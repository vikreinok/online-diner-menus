package ee.ttu.catering.config.authentication;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import ee.ttu.catering.rest.exception.TokenNotFoundException;
import ee.ttu.catering.rest.model.Token;
import ee.ttu.catering.rest.service.TokenService;

public class RestAuthenticationFilter implements Filter {

    private static final String TOKEN_HEADER_NAME = "X-Token";

    private final AuthenticationManager authenticationManager;
    private final RequestMatcher loginMatcher;
    private final RequestMatcher logoutMatcher;
    private final TokenService tokenService;

    public RestAuthenticationFilter(
            TokenService tokenService,
            AuthenticationManager authenticationManager,
            AntPathRequestMatcher loginMatcher,
            AntPathRequestMatcher logoutMatcher
    ) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.loginMatcher = loginMatcher;
        this.logoutMatcher = logoutMatcher;
    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        SecurityContext securityContext = SecurityContextHolder.getContext();

        if (loginMatcher.matches(httpRequest)) {
            try {
                String[] tokenData = extractAndDecodeHeader(httpRequest);
                String username = tokenData[0];
                String password = tokenData[1];
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
                Token token = tokenService.generateToken(username);
                httpResponse.setHeader(TOKEN_HEADER_NAME, token.getValue());
                httpResponse.setStatus(HttpServletResponse.SC_OK);
            }
            catch (AuthenticationException ex) {
                httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Authentication failed");
            }
            return;
        }

        String httpToken = httpRequest.getHeader(TOKEN_HEADER_NAME);
        if (httpToken == null) {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token missing");
            return;
        }

        try {
            Token token = tokenService.getToken(httpToken);
            securityContext.setAuthentication(new TokenAuthentication(token));

            if (logoutMatcher.matches(httpRequest)) {
                tokenService.deleteToken(token);
                httpResponse.setStatus(HttpServletResponse.SC_OK);
                return;
            }
        }
        catch (TokenNotFoundException ex) {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
            return;
        }

        chain.doFilter(httpRequest, httpResponse);
    }

    private String[] extractAndDecodeHeader(HttpServletRequest request) throws IOException {

        String header = request.getHeader("Authorization");

        if (header == null) {
            throw new BadCredentialsException("Missing basic authentication token");
        }

        byte[] base64Token = header.getBytes("UTF-8");
        byte[] decoded;
        try {
            decoded = Base64.decode(base64Token);
        }
        catch (IllegalArgumentException e) {
            throw new BadCredentialsException("Failed to decode basic authentication token");
        }

        String token = new String(decoded, "UTF-8");

        int delim = token.indexOf(':');

        if (delim == -1) {
            throw new BadCredentialsException("Invalid basic authentication token");
        }
        String[] tokenData = new String[]{token.substring(0, delim), token.substring(delim + 1)};
        if (tokenData.length != 2) {
            throw new BadCredentialsException("Malformed basic authentication token");
        }
        return tokenData;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

}

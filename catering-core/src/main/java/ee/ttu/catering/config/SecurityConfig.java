package ee.ttu.catering.config;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
@ImportResource(value = {"classpath:applicationContextSecurity.xml"})
public class SecurityConfig  {
	
	@Bean
	public RestAuthenticationEntryPoint restAuthenticationEntryPoint() {
		return new RestAuthenticationEntryPoint();
	}
	
	public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
		@Override
		public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)throws IOException {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
		}
	}
	

}

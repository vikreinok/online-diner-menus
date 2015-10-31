package ee.ttu.catering.config;

import ee.ttu.catering.config.authentication.RestAuthenticationFilter;
import ee.ttu.catering.config.authentication.UnauthenticationEntryPoint;
import ee.ttu.catering.rest.service.TokenService;
import ee.ttu.catering.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebMvcSecurity
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private UnauthenticationEntryPoint unauthenticationEntryPoint;
    
    @Autowired
    private TokenService tokenService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        RestAuthenticationFilter restAuthenticationFilter
                = new RestAuthenticationFilter(
                        tokenService,
                        authenticationManager(),
                        new AntPathRequestMatcher("/rest/login", HttpMethod.POST.name()),
                        new AntPathRequestMatcher("/rest/logout", HttpMethod.DELETE.name())
                );
        
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/", "/static/**").permitAll()
            .antMatchers("/rest/login").permitAll()
            .antMatchers("/rest/logout").permitAll()
            .antMatchers("/rest/**").authenticated()
            .anyRequest().denyAll().and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .exceptionHandling().authenticationEntryPoint(unauthenticationEntryPoint).and()
            .antMatcher("/rest/**")
	        .addFilterBefore(restAuthenticationFilter, BasicAuthenticationFilter.class);
        
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userService)
            .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
    	web.ignoring()
    	.antMatchers(HttpMethod.GET, "/rest/diner/**")
    	.antMatchers(HttpMethod.GET, "/rest/menu/**")
    	.antMatchers(HttpMethod.GET, "/rest/menu_item/**")
    	.antMatchers(HttpMethod.GET, "/rest/file/image/**")
    	.antMatchers(HttpMethod.POST, "/rest/diner/comment/**");
    }

}

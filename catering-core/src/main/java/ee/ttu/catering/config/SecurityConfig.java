package ee.ttu.catering.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(value = {"classpath:applicationContextSecurity.xml"})
public class SecurityConfig  {

}

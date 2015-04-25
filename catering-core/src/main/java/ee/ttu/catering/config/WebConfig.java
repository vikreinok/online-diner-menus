package ee.ttu.catering.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Spring Web configuration in replacement for web.xml.
 * It's a Spring overlay for the new Servlet 3.0 Java configuration.
 * By implementing WebApplicationInitializer, Spring will automatically call this class as web application configuration
 */
@Configuration
@EnableWebMvc
public class WebConfig {

}

package ee.ttu.catering.config;

import ee.ttu.catering.rest.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulingConfiguration {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    
    @Value("${app.token.timeout}")
    private Integer timeout;

    @Autowired
    private TokenService tokenService;

    @Scheduled(
            fixedRate = 1000 * 60,
            initialDelay = 0
    )
    public void deleteTokens() {
        LOGGER.debug("Runnging cleanup service");
        tokenService.deleteOlderThan(timeout);
    }

}
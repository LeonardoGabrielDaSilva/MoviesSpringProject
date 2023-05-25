package pt.com.leogds.infra.general;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;

@Configuration
public class Resilience4jConfig {

    @Bean
    public RateLimiterConfig rateLimiterConfig() {
        return RateLimiterConfig.custom()
                .limitForPeriod(1000) // max number of calls within a duration
                .limitRefreshPeriod(Duration.ofMinutes(1)) // duration for the limit
                .timeoutDuration(Duration.ofSeconds(10)) // max wait time to acquire a permit
                .build();
    }

    @Bean
    public RateLimiterRegistry rateLimiterRegistry(RateLimiterConfig rateLimiterConfig) {
        return RateLimiterRegistry.of(rateLimiterConfig);
    }
}
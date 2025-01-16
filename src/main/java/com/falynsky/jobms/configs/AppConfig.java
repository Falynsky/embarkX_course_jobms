package com.falynsky.jobms.configs;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration class for communication between ms without FeignClient.
 */
@Configuration
public class AppConfig {

    //leave if not every call is openFeign
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

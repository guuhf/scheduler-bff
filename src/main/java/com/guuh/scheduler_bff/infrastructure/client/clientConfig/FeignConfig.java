package com.guuh.scheduler_bff.infrastructure.client.clientConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public FeignError feignError(){
        return new FeignError();
    }
}

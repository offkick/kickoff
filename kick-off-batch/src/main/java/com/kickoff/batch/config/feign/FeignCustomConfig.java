package com.kickoff.batch.config.feign;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class FeignCustomConfig {

    private final TokenService tokenService;

    @Bean
    public AuthRequestInterceptor feignInterceptor()
    {
        return new AuthRequestInterceptor(tokenService);
    }
}

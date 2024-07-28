package com.kickoff.batch.config.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthRequestInterceptor implements RequestInterceptor {

    private final TokenService tokenService;

    @Override
    public void apply(RequestTemplate template)
    {
        template.header("X-Auth-Token", tokenService.getToken());
    }
}

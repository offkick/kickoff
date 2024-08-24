package com.kickoff.batch.config.feign;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    @Value("${api.footbool.token}")
    private String token;

    public String getToken()
    {
        return token;
    }
}

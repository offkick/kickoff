package com.kickoff.global.config.security;

public class CustomJWTException extends RuntimeException {

    public CustomJWTException(String message)
    {
        super(message);
    }
}


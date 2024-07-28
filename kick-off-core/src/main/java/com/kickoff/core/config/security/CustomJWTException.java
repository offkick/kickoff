package com.kickoff.core.config.security;

public class CustomJWTException extends RuntimeException {

    public CustomJWTException(String message)
    {
        super(message);
    }
}


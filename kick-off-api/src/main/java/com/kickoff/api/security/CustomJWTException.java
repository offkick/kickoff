package com.kickoff.api.security;

public class CustomJWTException extends RuntimeException {

    public CustomJWTException(String message)
    {
        super(message);
    }
}


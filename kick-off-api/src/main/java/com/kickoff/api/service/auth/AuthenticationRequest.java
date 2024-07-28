package com.kickoff.api.service.auth;

public record AuthenticationRequest(
        String email,
        String password
) {
    public static AuthenticationRequest of(String email, String password)
    {
        return new AuthenticationRequest(email, password);
    }
}

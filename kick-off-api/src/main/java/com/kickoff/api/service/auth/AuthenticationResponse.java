package com.kickoff.api.service.auth;

public record AuthenticationResponse(
        String accessToken,
        String refreshToken,
        String email
) {}

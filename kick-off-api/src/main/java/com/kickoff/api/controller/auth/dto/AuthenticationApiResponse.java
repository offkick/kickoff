package com.kickoff.api.controller.auth.dto;

import com.kickoff.api.service.auth.AuthenticationResponse;

public record AuthenticationApiResponse(
        String accessToken,
        String refreshToken,
        String email
) {
    public static AuthenticationApiResponse of(AuthenticationResponse response)
    {
        return new AuthenticationApiResponse(response.accessToken(), response.refreshToken(), response.email());
    }
}

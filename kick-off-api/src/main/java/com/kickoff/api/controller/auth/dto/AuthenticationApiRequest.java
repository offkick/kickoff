package com.kickoff.api.controller.auth.dto;

public record AuthenticationApiRequest (
        String email,
        String password
) {}

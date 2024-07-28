package com.kickoff.api.controller.member;

public record PasswordChangeRequest(
        String newPassword,
        String currentPassword
) {}

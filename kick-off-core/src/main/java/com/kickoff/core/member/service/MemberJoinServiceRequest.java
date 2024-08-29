package com.kickoff.core.member.service;

import com.kickoff.core.member.MemberRole;

public record MemberJoinServiceRequest(
        String nickname,
        String password,
        String confirmPassword,
        String email,
        MemberRole role
) {}

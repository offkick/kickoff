package com.kickoff.api.controller.member.dto;

import com.kickoff.core.member.MemberRole;
import com.kickoff.core.member.service.MemberJoinServiceRequest;

public record MemberJoinRequest(
        String nickName,
        String password,
        String confirmPassword,
        String email,
        MemberRole role
) {
    public MemberJoinServiceRequest toServiceDto()
    {
        return new MemberJoinServiceRequest(
                this.nickName,
                this.password(),
                this.confirmPassword,
                this.email(),
                MemberRole.USER
        );
    }
}

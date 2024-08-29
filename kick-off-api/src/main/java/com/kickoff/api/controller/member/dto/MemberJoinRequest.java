package com.kickoff.api.controller.member.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kickoff.core.member.MemberRole;
import com.kickoff.core.member.service.MemberJoinServiceRequest;
import jakarta.validation.constraints.NotBlank;

public record MemberJoinRequest(
        @NotBlank(message = "닉네임은 필수입니다.")
        String nickName,
        @NotBlank(message = "패스워드는 필수입니다.")
        String password,
        @NotBlank(message = "패스워드 확인은 필수입니다.")
        String confirmPassword,
        @NotBlank(message = "이메일은 필수입니다.")
        String email,
        @JsonIgnore
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

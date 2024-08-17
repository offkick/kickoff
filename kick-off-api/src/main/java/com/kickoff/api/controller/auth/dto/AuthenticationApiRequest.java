package com.kickoff.api.controller.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record AuthenticationApiRequest (
        @Email(message = "email 형식이 아닙니다.")
        @NotBlank(message = "이메일은 필수 입니다.")
        String email,

        @Length(min = 8, max = 15, message = "비밀번호는 8 ~ 15 글자입니다.")
        @NotBlank(message = "비밀번호는 필수입니다.")
        String password
) {}

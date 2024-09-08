package com.kickoff.api.service.board.member.dto;

public record CreateMemberServiceRequest(
        String nickname,
        String password
) {
}

package com.kickoff.api.service.board.member.dto;

public record CreateMemberServiceRequest(
        String nickName,
        String password
) {
}

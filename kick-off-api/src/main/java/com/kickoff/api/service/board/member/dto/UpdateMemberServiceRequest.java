package com.kickoff.api.service.board.member.dto;

public record UpdateMemberServiceRequest(
        String nickName,
        String password,
        Long memberId
) {
}

package com.kickoff.api.service.board.member;

public record MemberInfoResponse(
        Long memberId,
        String email,
        String nickname
) {
}

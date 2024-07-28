package com.kickoff.api.service.board.postlike.dto;

public record PostLikeServiceRequest(
        Long postId,
        Long memberId
) {
}

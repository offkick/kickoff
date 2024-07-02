package com.kickoff.api.service.board.postComment.dto;

public record UpdateCommentServiceRequest(
        String comment,
        Long commentId
) {
}

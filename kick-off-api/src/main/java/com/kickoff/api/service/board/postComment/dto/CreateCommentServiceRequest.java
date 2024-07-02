package com.kickoff.api.service.board.postComment.dto;

public record CreateCommentServiceRequest(
        Long commentId,
        String comment

) {
}

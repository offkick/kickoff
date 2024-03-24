package com.kickoff.api.service.board.post.dto;

public record UpdatePostServiceRequest(
        String title,
        String content,
        String category,
        Long postId

) {

}

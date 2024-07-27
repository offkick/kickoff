package com.kickoff.api.service.board.post.dto;

import com.kickoff.core.board.post.dto.CreatePostServiceRequest;

public record CreatePostRequest(
        String title,
        String content,
        String category
) {
    public CreatePostServiceRequest toServiceDto()
    {
        return new CreatePostServiceRequest(this.title, this.content, this.category);
    }
}

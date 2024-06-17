package com.kickoff.api.service.board.post.dto;

public record CreatePostServiceRequest(
        String title,
        String content,
        String category
) {
        public CreatePostServiceRequest toServiceDto()
        {
                return new CreatePostServiceRequest(title,content,category);
        }
}

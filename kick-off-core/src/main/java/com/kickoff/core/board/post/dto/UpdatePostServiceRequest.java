package com.kickoff.core.board.post.dto;

public record UpdatePostServiceRequest(
        String title,
        String content,
        String category
) {
    public UpdatePostServiceRequest toServiceDto()
    {
        return new UpdatePostServiceRequest(title, content, category);
    }
}

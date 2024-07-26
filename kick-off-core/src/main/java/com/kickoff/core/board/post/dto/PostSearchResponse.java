package com.kickoff.core.board.post.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kickoff.core.board.post.Post;

import java.time.LocalDateTime;

public record PostSearchResponse(
        Long postId,
        String title,
        String content,
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        LocalDateTime postRegisterDate,
        String postCategory,
        Long memberId,
        String memberName,
        int viewCount,
        int likeCount,
        int commentSize
) {
    public static PostSearchResponse of(Post post, int likeCount, int commentSize)
    {
        return new PostSearchResponse(
                post.getPostId(),
                post.getTitle(),
                post.getContent(),
                post.getPostDate(),
                post.getCategory(),
                post.getMember().getMemberId(),
                post.getMember().getNickName(),
                post.getViewCount(),
                likeCount,
                commentSize
        );
    }
}

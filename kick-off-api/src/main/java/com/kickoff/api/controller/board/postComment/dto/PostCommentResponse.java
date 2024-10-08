package com.kickoff.api.controller.board.postComment.dto;

import com.kickoff.core.board.postcomment.PostComment;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
public record PostCommentResponse(
        List<FindComment> findComments
) {
    public static PostCommentResponse of(List<PostComment> comments)
    {
        List<FindComment> findComments = comments.stream()
                .map(FindComment::from)
                .sorted((a, b) -> b.createdAt.compareTo(a.createdAt))
                .collect(Collectors.toList());
        return PostCommentResponse.builder()
                .findComments(findComments)
                .build();
    }
    public record FindComment(
            String comment,
            Long commentId,
            Long postId,
            Long memberId,
            String memberName,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    )
    {
        public static FindComment from(PostComment postComment)
        {
            return new FindComment(
                    postComment.getComment(),
                    postComment.getCommentId(),
                    postComment.getPost().getPostId(),
                    postComment.getMember().getMemberId(),
                    postComment.getMember().getNickname(),
                    postComment.getCreatedAt(),
                    postComment.getUpdatedAt()
            );
        }
    }
}

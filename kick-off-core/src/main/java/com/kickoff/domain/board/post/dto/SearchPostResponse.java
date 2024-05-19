package com.kickoff.domain.board.post.dto;

import com.kickoff.domain.board.post.Post;
import com.kickoff.domain.board.postcomment.PostComment;

import java.util.List;
import java.util.stream.Collectors;

public record SearchPostResponse(
        Long postId,
        String title,
        String content,
        String category,
        Long memberId,
        String memberName,
        List<CommentResponse> comments
) {
    public static SearchPostResponse from(List<PostComment> postComments)
    {
        if (postComments.isEmpty())
        {
            return null;
        }

        Post post = postComments.stream()
                .map(PostComment::getPost)
                .findFirst()
                .orElseThrow();

        return new SearchPostResponse(
                post.getPostId(),
                post.getTitle(),
                post.getContent(),
                post.getCategory(),
                post.getMember().getMemberId(),
                post.getMember().getNickName(),
                postComments.stream().map(CommentResponse::from).collect(Collectors.toList())
        );
    }
    public record CommentResponse(
            Long commentId,
            String comment,
            Long memberId,
            String memberName
    ){
        public static CommentResponse from(PostComment postComment)
        {
            return new CommentResponse(
                    postComment.getCommentId(),
                    postComment.getComment(),
                    postComment.getMember().getMemberId(),
                    postComment.getMember().getNickName()
            );
        }
    }
}

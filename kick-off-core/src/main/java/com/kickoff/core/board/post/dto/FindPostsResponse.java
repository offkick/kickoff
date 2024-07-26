package com.kickoff.core.board.post.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kickoff.core.board.post.Post;
import com.kickoff.core.board.postlike.PostLike;
import lombok.Builder;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Builder
public record FindPostsResponse(List<FindPost> findPosts, int totalPages, long totalElements) {
    public static FindPostsResponse of(Page<Post> posts, List<PostLike> postLikes)
    {
        Map<Long, Long> postLikeMap = postLikes.stream()
                .collect(Collectors.groupingBy(s -> s.getPost().getPostId(), Collectors.counting()));

        List<FindPost> findPosts = posts.stream()
                .map(post -> FindPost.from(post, postLikeMap.getOrDefault(post.getPostId(), 0L)))
                .collect(Collectors.toList());

        return FindPostsResponse.builder()
                .findPosts(findPosts)
                .totalElements(posts.getTotalElements())
                .totalPages(posts.getTotalPages())
                .build();
    }

    public record FindPost(
            Long postId,
            String title,
            String content,
            @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
            LocalDateTime postRegisterDate,
            String postCategory,
            Long memberId,
            String memberName,
            int viewCount,
            long likeCount
    ) {
        public static FindPost from(Post post, long likeCount)
        {
            return new FindPost(
                    post.getPostId(),
                    post.getTitle(),
                    post.getContent(),
                    post.getPostDate(),
                    post.getCategory(),
                    post.getMember().getMemberId(),
                    post.getMember().getNickName(),
                    post.getViewCount(),
                    likeCount
            );
        }
    }

}

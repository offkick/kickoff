package com.kickoff.core.board.post.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kickoff.core.board.post.Post;
import com.kickoff.core.board.postImage.PostImage;
import lombok.Builder;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
public record FindPostsResponse(
        List<FindPost> findPosts,
        int totalPages,
        long totalElements
) {
    public static FindPostsResponse of(Page<Post> posts)
    {
        List<FindPost> findPosts = posts.stream()
                .map(FindPost::from)
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
            String memberName
    ){
        public static FindPost from(Post post)
        {
//            Set<PostImage> postImages = post.getPostImages;
//            List<String> postImageList = postImages.stream()
//                    .sorted(Comparator.comparing(PostImage::getPostImageOrder))
//                    .map(PostImage::getUrl)
//                    .collect(Collectors.toList());
            return new FindPost(
                    post.getPostId(),
                    post.getTitle(),
                    post.getContent(),
                    post.getPostDate(),
                    post.getCategory(),
                    post.getMember().getMemberId(),
                    post.getMember().getNickName()
            );

        }

    }

}

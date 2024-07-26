package com.kickoff.core.board.post;

import com.kickoff.core.board.post.dto.FindPostCond;
import com.kickoff.core.board.post.dto.FindPostResponse;
import com.kickoff.core.board.post.dto.FindPostsResponse;
import com.kickoff.core.board.postcomment.PostCommentRepository;
import com.kickoff.core.board.postlike.PostLike;
import com.kickoff.core.board.postlike.PostLikeRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Component
@RequiredArgsConstructor
public class PostQuerydslRepository {
    private final JPAQueryFactory jpaQueryFactory;
    private final PostLikeRepository postLikeRepository;
    private final PostCommentRepository postCommentRepository;

    public FindPostsResponse findPosts(FindPostCond condition)
    {
        QPost post = QPost.post;
        Pageable pageable = condition.pageable();
        List<Post> posts = jpaQueryFactory.select(post)
                .from(post)
                .where(categoryEq(condition.postCategory()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Set<Long> postIds = posts.stream()
                .map(Post::getPostId).collect(Collectors.toSet());

        List<PostLike> byPostIds = postLikeRepository.findByPostIds(postIds);

        Long count = jpaQueryFactory.select(post.postId.count())
                .from(post)
                .where(categoryEq(condition.postCategory()))
                .fetchOne();

        return FindPostsResponse.of(
                new PageImpl<>(
                        posts,
                        pageable,
                        count
                ),
                byPostIds
        );
    }

    private BooleanExpression categoryEq(String category)
    {
        return category != null ? QPost.post.category.eq(category) : null;
    }

    @Transactional
    public FindPostResponse findPost(Long postId)
    {
        QPost qPost = QPost.post;
        Post post = jpaQueryFactory.select(qPost)
                .from(qPost)
                .where(qPost.postId.eq(postId))
                .fetchOne();
        post.addViewCount();
        int likeCount = postLikeRepository.countByPostId(post.getPostId());
        int commentSize = postCommentRepository.findCommentsByPostId(post.getPostId()).size();

        return FindPostResponse.of(post, likeCount, commentSize);
    }
}

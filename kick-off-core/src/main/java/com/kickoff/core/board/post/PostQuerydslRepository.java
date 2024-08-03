package com.kickoff.core.board.post;

import com.kickoff.core.board.post.dto.PostSearchCondition;
import com.kickoff.core.board.post.dto.PostSearchResponse;
import com.kickoff.core.board.post.dto.PostSearchResponses;
import com.kickoff.core.board.postcomment.PostCommentRepository;
import com.kickoff.core.board.postlike.PostLike;
import com.kickoff.core.board.postlike.PostLikeRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Component
@RequiredArgsConstructor
@Log4j2
public class PostQuerydslRepository {
    private final JPAQueryFactory jpaQueryFactory;
    private final PostLikeRepository postLikeRepository;
    private final PostCommentRepository postCommentRepository;

    public PostSearchResponses findPosts(PostSearchCondition condition)
    {
        QPost post = QPost.post;
        Pageable pageable = condition.pageable();
        List<Post> posts = jpaQueryFactory.select(post)
                .from(post)
                .where(categoryEq(condition.postCategory()).and(post.isDeleted.isFalse()))
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

        return PostSearchResponses.of(
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
    public PostSearchResponse findPost(Long postId)
    {
        QPost qPost = QPost.post;
        Post post = jpaQueryFactory.select(qPost)
                .from(qPost)
                .where(qPost.postId.eq(postId).and(qPost.isDeleted.isFalse()))
                .fetchOne();

        if (post != null)
        {
            int likeCount = postLikeRepository.countByPostId(post.getPostId());
            int commentSize = postCommentRepository.findCommentsByPostId(post.getPostId()).size();
            return PostSearchResponse.of(post, likeCount, commentSize);
        }

        return null;
    }

    public PostSearchResponses findMemberPosts(Long memberId) {
        QPost post = QPost.post;
        List<Post> posts = jpaQueryFactory.select(post)
                .from(post)
                .where(post.member.memberId.eq(memberId))
                .fetch();

        Set<Long> postIds = posts.stream().map(Post::getPostId).collect(Collectors.toSet());

        List<PostLike> byPostIds = postLikeRepository.findByPostIds(postIds);

        Long count = jpaQueryFactory.select(post.postId.count())
                .from(post)
                .where(post.member.memberId.eq(memberId))
                .fetchOne();

        return PostSearchResponses.of(
                new PageImpl<>(
                        posts,
                        PageRequest.of(0, 100000),
                        count
                ),
                byPostIds
        );
    }
}

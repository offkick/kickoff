package com.kickoff.core.board.post;

import com.kickoff.core.board.post.dto.FindPostCond;
import com.kickoff.core.board.post.dto.FindPostsResponse;
import com.kickoff.core.board.post.dto.SearchPostResponse;
import com.kickoff.core.board.postcomment.PostComment;
import com.kickoff.core.board.postcomment.QPostComment;
import com.kickoff.core.member.QMember;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Component
@RequiredArgsConstructor
public class PostQuerydslRepository {
    private final JPAQueryFactory jpaQueryFactory;

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
        Long count = jpaQueryFactory.select(post.postId.count())
                .from(post)
                .where(categoryEq(condition.postCategory()))
                .fetchOne();
        return FindPostsResponse.of(
                new PageImpl<>(
                        posts,
                        pageable,
                        count
                )
        );

    }

    private BooleanExpression categoryEq(String category)
    {
        return category != null ? QPost.post.category.eq(category) : null;
    }




//    public SearchPostResponse searchPost(Long postId)
//    {
//        QPostComment postComment = QPostComment.postComment;
//        QPost post = QPost.post;
//        QMember member = QMember.member;
//        jpaQueryFactory.select(post)
//                .from(post)
//                .join(post.member, member).fetchJoin()
//                .where(
//
//                )
//        List<PostComment> postComments = jpaQueryFactory.selectFrom(postComment)
//                .leftJoin(postComment.post, QPost.post).fetchJoin()
//                .leftJoin(postComment.member, QMember.member).fetchJoin()
//                .where(postComment.post.postId.eq(postId))
//                .fetch();
//        return SearchPostResponse.from(postComments);
//    }
}

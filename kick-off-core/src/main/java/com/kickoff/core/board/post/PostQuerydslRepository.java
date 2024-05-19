package com.kickoff.core.board.post;

import com.kickoff.core.board.post.dto.SearchPostResponse;
import com.kickoff.core.board.postcomment.PostComment;
import com.kickoff.core.board.postcomment.QPostComment;
import com.kickoff.core.member.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Component
@RequiredArgsConstructor
public class PostQuerydslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public SearchPostResponse searchPost(Long postId)
    {
        QPostComment postComment = QPostComment.postComment;
        List<PostComment> postComments = jpaQueryFactory.selectFrom(postComment)
                .leftJoin(postComment.post, QPost.post).fetchJoin()
                .leftJoin(postComment.member, QMember.member).fetchJoin()
                .where(postComment.post.postId.eq(postId))
                .fetch();
        return SearchPostResponse.from(postComments);
    }
}

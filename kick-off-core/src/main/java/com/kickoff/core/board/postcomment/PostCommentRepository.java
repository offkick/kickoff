package com.kickoff.core.board.postcomment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface PostCommentRepository extends JpaRepository<PostComment,Long> {
    @Query("select c from PostComment c join fetch c.post where c.post.postId = :postId")
    List<PostComment> findCommentsByPostId(@Param("postId") Long postId);

    @Query("select c from PostComment c join fetch c.member where c.member.memberId = :memberId")
    List<PostComment> findCommentsByMemberId(@Param("memberId") Long memberId);


    @Query("SELECT p.post.postId, COUNT(p) " +
            "FROM PostComment p " +
            "INNER JOIN p.post " +
            "WHERE p.post.postId IN :postIds " +
            "GROUP BY p.post.postId")
    List<Object[]> countCommentsByPostIds(@Param("postIds") Set<Long> postIds);
}

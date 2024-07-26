package com.kickoff.core.board.postlike;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface PostLikeRepository extends JpaRepository<PostLike,Long> {
    @Query("select count(pl) from PostLike pl where pl.post.postId = :postId")
    int countByPostId(@Param("postId") Long postId);

    @Query("select pl from PostLike pl where pl.post.postId in :postIds")
    List<PostLike> findByPostIds(@Param("postIds")Set<Long> postIds);
}

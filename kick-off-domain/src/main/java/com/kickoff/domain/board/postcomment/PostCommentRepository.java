package com.kickoff.domain.board.postcomment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommentRepository extends JpaRepository<PostComment,Long> {
}

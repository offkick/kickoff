package com.kickoff.core.soccer.player;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerCommentRepository extends JpaRepository<PlayerComment, Long> {
    List<PlayerComment> findByPlayerIdOrderByCreatedAt(Long playerId);
}

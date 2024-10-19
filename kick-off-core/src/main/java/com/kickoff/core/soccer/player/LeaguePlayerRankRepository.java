package com.kickoff.core.soccer.player;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaguePlayerRankRepository extends JpaRepository<LeaguePlayerRank, Long> {
    boolean existsByMatchDayAndPlayerId(Long matchDay, Long playerId);
}

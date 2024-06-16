package com.kickoff.core.soccer.team.league.game;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface LeagueGameRepository extends JpaRepository<LeagueGame, Long> {

    List<LeagueGame> findByGameDateBetween(LocalDateTime start, LocalDateTime end);
}

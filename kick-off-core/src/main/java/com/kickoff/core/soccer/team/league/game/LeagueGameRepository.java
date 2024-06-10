package com.kickoff.core.soccer.team.league.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LeagueGameRepository extends JpaRepository<LeagueGame, Long> {

    List<LeagueGame> findByGameDateBetween(LocalDateTime start, LocalDateTime end);
}

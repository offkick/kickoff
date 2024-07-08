package com.kickoff.core.soccer.team.league.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface LeagueGameRepository extends JpaRepository<LeagueGame, Long> {

    List<LeagueGame> findByGameDateBetween(LocalDateTime start, LocalDateTime end);

    @Query("select g from LeagueGame g where g.home.league.leagueId = :leagueId and g.gameDate >= :start and g.gameDate <= :end ")
    List<LeagueGame> findBySeasonBetween(@Param("leagueId") Long leagueId, @Param("start")LocalDateTime start, @Param("end") LocalDateTime end);
}

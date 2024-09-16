package com.kickoff.core.soccer.league.game;

import com.kickoff.core.soccer.league.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LeagueGameRepository extends JpaRepository<LeagueGame, Long> {

    List<LeagueGame> findByGameDateBetween(LocalDateTime start, LocalDateTime end);


    List<LeagueGame> findBySeason(Season season);

    @Query("select g from LeagueGame g where g.home.league.leagueId = :leagueId and g.gameDate >= :start and g.gameDate <= :end ")
    List<LeagueGame> findBySeasonBetween(@Param("leagueId") Long leagueId, @Param("start")LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("select g from LeagueGame g where (g.home.leagueTeamId = :leagueTeamId or g.away.leagueTeamId = :leagueTeamId) and g.gameDate >= :start and g.gameDate <= :end")
    List<LeagueGame> findByLeagueTeam(@Param("leagueTeamId") Long leagueTeamId,@Param("start")LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("select g from LeagueGame g where g.away.leagueTeamId = :awayTeamId and g.home.leagueTeamId = :homeTeamId and g.matchDay = :matchDay")
    Optional<LeagueGame> findByTeamsAndMatchDay(@Param("awayTeamId") Long awayTeamId, @Param("homeTeamId") Long homeTeamId, @Param("matchDay") int matchDay);



}

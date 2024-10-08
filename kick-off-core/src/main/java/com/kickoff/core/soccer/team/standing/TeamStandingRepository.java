package com.kickoff.core.soccer.team.standing;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeamStandingRepository extends JpaRepository<TeamStanding, Long> {
    boolean existsBySeasonAndRound(String season, long round);
    boolean existsByTeamIdAndSeasonAndRoundAndLeagueId(Long teamId, String season, long round, Long leagueId);
    Optional<TeamStanding> findByTeamIdAndSeasonAndRoundAndLeagueId(Long teamId, String season, long round, Long leagueId);
    List<TeamStanding> findBySeason(String season);
    List<TeamStanding> findBySeasonAndRound(String season, Long round);
}

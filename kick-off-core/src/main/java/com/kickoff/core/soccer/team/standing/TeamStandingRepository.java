package com.kickoff.core.soccer.team.standing;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamStandingRepository extends JpaRepository<TeamStanding, Long> {
    boolean existsBySeasonAndRound(String season, long round);
    List<TeamStanding> findBySeason(String season);
    List<TeamStanding> findBySeasonAndRound(String season, Long round);
}

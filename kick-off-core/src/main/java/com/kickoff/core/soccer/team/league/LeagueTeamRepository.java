package com.kickoff.core.soccer.team.league;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeagueTeamRepository extends JpaRepository<LeagueTeam, Long> {
    Optional<LeagueTeam> findByLeagueTeamName(String leagueTeamName);
}

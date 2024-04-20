package com.kickoff.domain.soccer.team.league;

import com.kickoff.domain.team.league.LeagueTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeagueTeamRepository extends JpaRepository<LeagueTeam, Long> {
    Optional<LeagueTeam> findByLeagueTeamName(String leagueTeamName);
}

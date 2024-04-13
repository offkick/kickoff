package com.kickoff.domain.soccer.team.league;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueTeamRepository extends JpaRepository<LeagueTeam, Long> {
}

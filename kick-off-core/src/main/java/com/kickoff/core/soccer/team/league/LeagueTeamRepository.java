package com.kickoff.core.soccer.team.league;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeagueTeamRepository extends JpaRepository<LeagueTeam, Long> {
    Optional<LeagueTeam> findByLeagueTeamName(String leagueTeamName);
    @Query("select l from LeagueTeam l where l.league.leagueName = :leagueName")
    List<LeagueTeam> findByLeagueName(@Param("leagueName") String leagueName);
}

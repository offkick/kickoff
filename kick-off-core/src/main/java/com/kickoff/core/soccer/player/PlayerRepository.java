package com.kickoff.core.soccer.player;

import com.kickoff.core.soccer.team.league.LeagueTeam;
import com.kickoff.core.soccer.team.league.Season;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByPlayerId(Long playerId);
    List<Player> findByLeagueTeamAndSeason(LeagueTeam leagueTeam, Season season);
}

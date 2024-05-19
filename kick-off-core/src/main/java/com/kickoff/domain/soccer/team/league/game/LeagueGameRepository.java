package com.kickoff.domain.soccer.team.league.game;

import com.kickoff.domain.soccer.team.league.League;
import com.kickoff.domain.soccer.team.league.Season;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeagueGameRepository extends JpaRepository<LeagueGame, Long> {
    List<LeagueGame> findAllByLeagueAndSeason(League league, Season season);
}

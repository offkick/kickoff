package com.kickoff.core.soccer.league.game.dto;

import com.kickoff.core.soccer.league.LeagueTeam;
import com.kickoff.core.soccer.team.Score;
import com.kickoff.core.soccer.league.Season;
import com.kickoff.core.soccer.league.game.LeagueGame;
import com.kickoff.core.soccer.league.game.LeagueGameStatus;
import com.kickoff.core.soccer.league.game.player.LeagueGamePlayer;

import java.time.LocalDateTime;
import java.util.List;

public record FindLeagueGameResponse (
        Long leagueGameId,
        LocalDateTime gameDate,
        int count,
        LeagueTeam away,
        LeagueTeam home,
        Score score,
        LeagueGameStatus leagueGameStatus,
        Season season,
        List<LeagueGamePlayer> homePlayers,
        List<LeagueGamePlayer> awayPlayers


){
    public static FindLeagueGameResponse from(LeagueGame leagueGame) {


        return new FindLeagueGameResponse(
                leagueGame.getLeagueGameId(),
                leagueGame.getGameDate(),
                leagueGame.getMatchDay(),
                leagueGame.getAway(),
                leagueGame.getHome(),
                leagueGame.getScore(),
                leagueGame.getLeagueGameStatus(),
                leagueGame.getSeason(),
                leagueGame.getHomePlayers(),
                leagueGame.getAwayPlayers()
        );
    }
}

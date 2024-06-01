package com.kickoff.admin.service.dto;

import com.kickoff.core.soccer.team.Score;
import com.kickoff.core.soccer.team.league.LeagueTeam;
import com.kickoff.core.soccer.team.league.Season;
import com.kickoff.core.soccer.team.league.game.LeagueGame;
import com.kickoff.core.soccer.team.league.game.LeagueGameStatus;
import com.kickoff.core.soccer.team.league.game.player.LeagueGamePlayer;

import java.time.LocalDateTime;
import java.util.List;

public record FindLeagueGameResponses(
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
    public static FindLeagueGameResponses from(LeagueGame leagueGame) {
        return new FindLeagueGameResponses(
                leagueGame.getLeagueGameId(),
                leagueGame.getGameDate(),
                leagueGame.getCount(),
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
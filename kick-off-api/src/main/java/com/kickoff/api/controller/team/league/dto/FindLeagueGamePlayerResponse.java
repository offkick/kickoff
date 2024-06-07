package com.kickoff.api.controller.team.league.dto;

import com.kickoff.core.soccer.team.Score;
import com.kickoff.core.soccer.team.league.LeagueTeam;
import com.kickoff.core.soccer.team.league.Season;
import com.kickoff.core.soccer.team.league.dto.GamePlayer;
import com.kickoff.core.soccer.team.league.game.LeagueGame;
import com.kickoff.core.soccer.team.league.game.LeagueGameStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


public record FindLeagueGamePlayerResponse(
        Long leagueGameId,
        LocalDateTime gameDate,
        int count,
        LeagueTeam away,
        LeagueTeam home,
        Score score,
        LeagueGameStatus leagueGameStatus,
        Season season,
        List<GamePlayer> homePlayers,
        List<GamePlayer> awayPlayers


){
    public static FindLeagueGamePlayerResponse from(LeagueGame leagueGame) {
        List<GamePlayer> homePlayers = leagueGame.getHomePlayers().stream()
                .map(player -> new GamePlayer(
                        player.getPlayer().getPlayerName(),
                        player.getPlayedTime(),
                        player.getSubTime(),
                        player.getPosition()
                ))
                .collect(Collectors.toList());
        List<GamePlayer> awayPlayers = leagueGame.getAwayPlayers().stream()
                .map(player -> new GamePlayer(
                        player.getPlayer().getPlayerName(),
                        player.getPlayedTime(),
                        player.getSubTime(),
                        player.getPosition()

                ))
                .collect(Collectors.toList());


        return new FindLeagueGamePlayerResponse(
                leagueGame.getLeagueGameId(),
                leagueGame.getGameDate(),
                leagueGame.getCount(),
                leagueGame.getAway(),
                leagueGame.getHome(),
                leagueGame.getScore(),
                leagueGame.getLeagueGameStatus(),
                leagueGame.getSeason(),
                homePlayers,
                awayPlayers
        );
    }
}

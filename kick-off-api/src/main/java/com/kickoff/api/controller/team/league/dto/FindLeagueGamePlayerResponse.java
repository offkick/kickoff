package com.kickoff.api.controller.team.league.dto;

import com.kickoff.core.soccer.player.PlayerPosition;
import com.kickoff.core.soccer.team.Score;
import com.kickoff.core.soccer.team.league.Season;
import com.kickoff.core.soccer.team.league.game.LeagueGame;
import com.kickoff.core.soccer.team.league.game.LeagueGameStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


public record FindLeagueGamePlayerResponse(List<LeagueGamePlayerResponse> responses) {
    public static FindLeagueGamePlayerResponse of(List<LeagueGame> leagueGames)
    {
        List<LeagueGamePlayerResponse> leagueGamePlayerResponses = leagueGames.stream()
                .map(LeagueGamePlayerResponse::of)
                .collect(Collectors.toList());

        return new FindLeagueGamePlayerResponse(leagueGamePlayerResponses);
    }

    public static FindLeagueGamePlayerResponse from(LeagueGame leagueGame)
    {
        return new FindLeagueGamePlayerResponse(
                List.of(LeagueGamePlayerResponse.of(leagueGame))
        );
    }

    public record GamePlayer(
            String name,
            int playedTime,
            int subTime,
            PlayerPosition position
    ) {}

    public record LeagueGamePlayerResponse(
            Long leagueGameId,
            LocalDateTime gameDate,
            int count,
            String away,
            String home,
            Score score,
            LeagueGameStatus leagueGameStatus,
            Season season,
            List<GamePlayer> homePlayers,
            List<GamePlayer> awayPlayers
    ) {
        public static LeagueGamePlayerResponse of(LeagueGame leagueGame){
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

            return new LeagueGamePlayerResponse(
                    leagueGame.getLeagueGameId(),
                    leagueGame.getGameDate(),
                    leagueGame.getCount(),
                    leagueGame.getAway().getLeagueTeamName(),
                    leagueGame.getHome().getLeagueTeamName(),
                    leagueGame.getScore(),
                    leagueGame.getLeagueGameStatus(),
                    leagueGame.getSeason(),
                    homePlayers,
                    awayPlayers
            );
        }
    }
}

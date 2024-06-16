package com.kickoff.api.controller.team.league.dto;

import com.kickoff.core.soccer.team.Score;
import com.kickoff.core.soccer.team.league.LeagueTeam;
import com.kickoff.core.soccer.team.league.Season;
import com.kickoff.core.soccer.team.league.dto.FindLeagueGamePlayerResponse;
import com.kickoff.core.soccer.team.league.dto.GamePlayer;
import com.kickoff.core.soccer.team.league.game.LeagueGameStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


public record FindLeagueGamePlayerResponseDto(
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
    public static FindLeagueGamePlayerResponseDto from(FindLeagueGamePlayerResponse response) {
        List<GamePlayer> homePlayers = response.homePlayers().stream()
                .map(player -> new GamePlayer(
                        player.name(),
                        player.playedTime(),
                        player.subTime(),
                        player.position()
                ))
                .collect(Collectors.toList());
        List<GamePlayer> awayPlayers = response.awayPlayers().stream()
                .map(player -> new GamePlayer(
                        player.name(),
                        player.playedTime(),
                        player.subTime(),
                        player.position()

                ))
                .collect(Collectors.toList());


        return new FindLeagueGamePlayerResponseDto(
                response.leagueGameId(),
                response.gameDate(),
                response.count(),
                response.away(),
                response.home(),
                response.score(),
                response.leagueGameStatus(),
                response.season(),
                homePlayers,
                awayPlayers
        );
    }
}

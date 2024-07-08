package com.kickoff.api.controller.team.league.dto;

import com.kickoff.core.soccer.team.league.game.LeagueGame;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record SeasonLeagueGameResponse(
        List<DateResponse> responses
) {

    public static SeasonLeagueGameResponse of(List<LeagueGame> leagueGames) {
        Map<LocalDate, List<LeagueGame>> gamesByDate = leagueGames.stream()
                .collect(Collectors.groupingBy(game -> game.getGameDate().toLocalDate()));

        List<DateResponse> dateResponses = gamesByDate.entrySet().stream()
                .map(entry -> {
                    LocalDate date = entry.getKey();
                    List<LeagueGame> games = entry.getValue();
                    List<LeagueGameResponse> gameResponses = games.stream()
                            .map(LeagueGameResponse::of)
                            .collect(Collectors.toList());
                    return new DateResponse(date, gameResponses);
                })
                .collect(Collectors.toList());

        return new SeasonLeagueGameResponse(dateResponses);
    }

    public record DateResponse(
            LocalDate date,
            List<LeagueGameResponse> games
    ) {
    }

    public record LeagueGameResponse(
            LocalDateTime date,
            String homeTeamName,
            String awayTeamName,
            String homeScore,
            String awayScore,
            String homeLogo,
            String awayLogo
    ) {
        public static LeagueGameResponse of(LeagueGame leagueGame) {
            return new LeagueGameResponse(
                    leagueGame.getGameDate(),
                    leagueGame.getHome().getLeagueTeamName(),
                    leagueGame.getAway().getLeagueTeamName(),
                    leagueGame.getScore().getHomeScore(),
                    leagueGame.getScore().getAwayScore(),
                    leagueGame.getHome().getLogo(),
                    leagueGame.getAway().getLogo()
            );
        }
    }
}

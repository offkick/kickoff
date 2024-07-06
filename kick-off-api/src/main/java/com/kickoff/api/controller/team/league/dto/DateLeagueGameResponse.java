package com.kickoff.api.controller.team.league.dto;

import com.kickoff.core.soccer.team.league.game.LeagueGame;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record DateLeagueGameResponse(
        List<LeagueGameResponse> responses
) {

    public static DateLeagueGameResponse of(List<LeagueGame> leagueGames)
    {
        List<LeagueGameResponse> leagueGameResponses = leagueGames.stream()
                .map(LeagueGameResponse::of)
                .collect(Collectors.toList());
        return new DateLeagueGameResponse(leagueGameResponses);
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
        public static LeagueGameResponse of(LeagueGame leagueGame)
        {
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

package com.kickoff.api.controller.team.league.dto;

import com.kickoff.core.soccer.team.league.service.dto.LeagueGameDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record DateLeagueGameResponse(
        List<LeagueGameResponse> responses
) {

    public static DateLeagueGameResponse of(List<LeagueGameDTO> leagueGames)
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
            String awayLogo,
            String venue
    ) {
        public static LeagueGameResponse of(LeagueGameDTO leagueGame)
        {
            return new LeagueGameResponse(
                    leagueGame.gameDate(),
                    leagueGame.home().leagueTeamName(),
                    leagueGame.away().leagueTeamName(),
                    leagueGame.score().homeScore(),
                    leagueGame.score().awayScore(),
                    leagueGame.home().logo(),
                    leagueGame.away().logo(),
                    leagueGame.venue()
            );
        }
    }
}

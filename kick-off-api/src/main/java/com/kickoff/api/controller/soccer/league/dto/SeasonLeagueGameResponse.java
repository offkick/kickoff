package com.kickoff.api.controller.soccer.league.dto;

import com.kickoff.core.soccer.game.LeagueGameStatus;
import com.kickoff.core.soccer.league.service.dto.LeagueGameDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record SeasonLeagueGameResponse(
        List<DateResponse> responses
) {
    public static SeasonLeagueGameResponse of(List<LeagueGameDTO> leagueGames) {
        Map<LocalDate, List<LeagueGameDTO>> gamesByDate = leagueGames.stream()
                .collect(Collectors.groupingBy(s -> s.gameDate().toLocalDate()));

        List<DateResponse> dateResponses = gamesByDate.entrySet().stream()
                .map(entry -> {
                    LocalDate date = entry.getKey();
                    List<LeagueGameDTO> games = entry.getValue();
                    List<LeagueGameResponse> gameResponses = games.stream()
                            .map(LeagueGameResponse::of)
                            .sorted(Comparator.comparing(LeagueGameResponse::date))
                            .collect(Collectors.toList());
                    return new DateResponse(date, gameResponses);
                })
                .sorted(Comparator.comparing(DateResponse::date))
                .collect(Collectors.toList());

        return new SeasonLeagueGameResponse(dateResponses);
    }

    public record DateResponse(LocalDate date, List<LeagueGameResponse> games) {}

    public record TeamResponse(
            Long homeTeamId,
            Long awayTeamId
    ){
        public static TeamResponse of(LeagueGameDTO leagueGameDTO) {
            return new TeamResponse(
                    leagueGameDTO.home().leagueTeamId(),
                    leagueGameDTO.away().leagueTeamId()
            );

        }
    }

    public record LeagueGameResponse(
            Long leagueGameId,  
            LocalDateTime date,
            String homeTeamName,
            String awayTeamName,
            Long homeTeamId,
            Long awayTeamId,
            String homeScore,
            String awayScore,
            LeagueGameStatus leagueGameStatus,
            String homeLogo,
            String awayLogo,
            String venue,
            List<TeamResponse> teamId,
            int matchDay,
            List<Goals> goals
            ) {
        public static LeagueGameResponse of(LeagueGameDTO leagueGame) {
            return new LeagueGameResponse(
                    leagueGame.leagueGameId(),
                    leagueGame.gameDate(),
                    leagueGame.home().leagueTeamName(),
                    leagueGame.away().leagueTeamName(),
                    leagueGame.home().leagueTeamId(),
                    leagueGame.away().leagueTeamId(),
                    leagueGame.score().homeScore(),
                    leagueGame.score().awayScore(),
                    leagueGame.leagueGameStatus(),
                    leagueGame.home().logo(),
                    leagueGame.away().logo(),
                    leagueGame.venue(),
                    java.util.List.of(TeamResponse.of(leagueGame)),
                    leagueGame.matchDay(),
                    leagueGame.goalInfos().stream().map(Goals::of).collect(Collectors.toList())
            );
        }
    }

    public record Goals(
            Long plyerId,
            String playerName,
            int time,
            String type
    ) {
        public static Goals of(LeagueGameDTO.GoalInfo info)
        {
            return new Goals(info.playerId(), info.playerName(), info.playTime(), info.goalType());
        }
    }
}

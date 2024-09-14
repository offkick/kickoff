package com.kickoff.api.controller.soccer.league.dto;

import com.kickoff.core.soccer.player.PlayerPosition;
import com.kickoff.core.soccer.team.league.game.LeagueGameStatus;
import com.kickoff.core.soccer.team.league.service.dto.LeagueGameDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record FindLeagueGamePlayerResponse(
        LeagueGamePlayerResponse response
) {
    public static FindLeagueGamePlayerResponse of(LeagueGameDTO leagueGame)
    {
        return new FindLeagueGamePlayerResponse(LeagueGamePlayerResponse.of(leagueGame));
    }

    public record GamePlayer(
            String name,
            int playedTime,
            int subTime,
            PlayerPosition position
    ) {}

    public record LeagueGamePlayerResponse(
            LocalDateTime gameDate,
            int matchDay,
            String away,
            String home,
            String homeScore,
            String awayScore,
            LeagueGameStatus leagueGameStatus,
            String venue,
            List<GamePlayer> homePlayers,
            List<GamePlayer> awayPlayers,
            List<GoalsDTO> goals
    ) {
        public static LeagueGamePlayerResponse of(LeagueGameDTO leagueGame) {
            return new LeagueGamePlayerResponse(
                    leagueGame.gameDate(),
                    leagueGame.matchDay(),
                    leagueGame.away().leagueTeamName(),
                    leagueGame.home().leagueTeamName(),
                    leagueGame.score().homeScore(),
                    leagueGame.score().awayScore(),
                    leagueGame.leagueGameStatus(),
                    leagueGame.venue(),
                    null,
                    null,
                    leagueGame.goalInfos().stream().map(GoalsDTO::of).collect(Collectors.toList())
            );
        }
        public record GoalsDTO(
                Long plyerId,
                String playerName,
                int time,
                String type,
                String teamName,
                Long teamId
        ) {
            public static GoalsDTO of(LeagueGameDTO.GoalInfo info)
            {
                return new GoalsDTO(info.playerId(), info.playerName(), info.playTime(), info.goalType(),
                    info.teamName(),
                        info.teamId()
                );
            }
        }
    }
}

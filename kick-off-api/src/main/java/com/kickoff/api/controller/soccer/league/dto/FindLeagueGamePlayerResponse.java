package com.kickoff.api.controller.soccer.league.dto;

import com.kickoff.core.soccer.game.LeagueGameStatus;
import com.kickoff.core.soccer.league.service.dto.LeagueGameDTO;
import com.kickoff.core.soccer.league.service.dto.LeagueGamePlayerDTO;

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
            Long playerId,
            String position,
            String shirtNumber,
            String playerKrName,
            String playerEnName
    ) {
        public static List<GamePlayer> of(List<LeagueGamePlayerDTO> leagueGamePlayerDTOS)
        {
            return leagueGamePlayerDTOS.stream()
                    .map(s-> new GamePlayer(s.playerId(), s.position(), s.shirtNumber(), s.playerKrName(), s.playerEnName()))
                    .collect(Collectors.toList());
        }
    }

    public record LeagueGamePlayerResponse(
            LocalDateTime gameDate,
            int matchDay,
            String away,
            String home,
            String homeScore,
            String awayScore,
            String homeFormation,
            String awayFormation,
            String homeLogo,
            String awayLogo,
            LeagueGameStatus leagueGameStatus,
            String venue,
            List<GamePlayer> homePlayers,
            List<GamePlayer> awayPlayers,
            List<GoalsDTO> goals,
            Long minute,
            Long injuryTime
    ) {
        public static LeagueGamePlayerResponse of(LeagueGameDTO leagueGame) {
            return new LeagueGamePlayerResponse(
                    leagueGame.gameDate(),
                    leagueGame.matchDay(),
                    leagueGame.away().leagueTeamName(),
                    leagueGame.home().leagueTeamName(),
                    leagueGame.score().homeScore(),
                    leagueGame.score().awayScore(),
                    leagueGame.homeFormation(),
                    leagueGame.awayFormation(),
                    leagueGame.home().logo(),
                    leagueGame.away().logo(),
                    leagueGame.leagueGameStatus(),
                    leagueGame.venue(),
                    GamePlayer.of(leagueGame.homePlayers()),
                    GamePlayer.of(leagueGame.awayPlayers()),
                    leagueGame.goalInfos().stream().map(GoalsDTO::of).collect(Collectors.toList()),
                    leagueGame.minute(),
                    leagueGame.injuryTime()
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

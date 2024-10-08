package com.kickoff.api.controller.soccer.league.dto;

import com.kickoff.core.soccer.game.LeagueGameStatus;
import com.kickoff.core.soccer.league.service.dto.LeagueGameDTO;
import com.kickoff.core.soccer.league.service.dto.LeagueGamePlayerDTO;
import lombok.Builder;

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

    public record GameBooking(
            Long minute,
            Long leagueTeamId,
            Long playerId,
            String type,
            String playerKrName,
            String playerEnName,
            String cardType
    ) {
        public static GameBooking of(LeagueGameDTO.GameBookingDTO dto)
        {
            return new GameBooking(
                    dto.minute(),
                    dto.leagueTeamId(),
                    dto.playerId(),
                    dto.type(),
                    dto.playerKrName(),
                    dto.playerEnName(),
                    dto.cardType()
            );
        }
    }

    @Builder
    public record GameStatistics(
            String cornerKicks,
            String freeKicks,
            String goalKicks,
            String offsides,
            String fouls,
            String ballPossession,
            String saves,
            String throwIns,
            String shots,
            String shotsOnGoal,
            String shotsOffGoal,
            String yellowCards,
            String yellowRedCards,
            String redCards
    ) {
        public static GameStatistics of(LeagueGameDTO.GameStatisticsDTO statistics)
        {
            if (statistics ==  null)
            {
                return null;
            }
            return GameStatistics.builder()
                    .cornerKicks(statistics.cornerKicks())
                    .freeKicks(statistics.freeKicks())
                    .goalKicks(statistics.goalKicks())
                    .offsides(statistics.offsides())
                    .fouls(statistics.fouls())
                    .ballPossession(statistics.ballPossession())
                    .saves(statistics.saves())
                    .throwIns(statistics.throwIns())
                    .shots(statistics.shots())
                    .shotsOnGoal(statistics.shotsOnGoal())
                    .shotsOffGoal(statistics.shotsOffGoal())
                    .yellowCards(statistics.yellowCards())
                    .yellowRedCards(statistics.yellowRedCards())
                    .redCards(statistics.redCards())
                    .build();
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
            List<SubstitutionDTO> substitutions,
            Long minute,
            Long injuryTime,
            List<GameBooking> bookings,
            GameStatistics homeStatistics,
            GameStatistics awayStatistics
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
                    null,
                    leagueGame.minute(),
                    leagueGame.injuryTime(),
                    leagueGame.gameBookingDTOS().stream().map(GameBooking::of).collect(Collectors.toList()),
                    GameStatistics.of(leagueGame.homeGameStatisticsDTO()),
                    GameStatistics.of(leagueGame.awayGameStatisticsDTO())
            );
        }
        public record GoalsDTO(
                Long playerId,
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

        public record SubstitutionDTO(
                int minute,
                Long playerOutId,
                String playerOutName,
                Long playerInId,
                String playerInName,
                String substitutionTeam
        ){
            public static SubstitutionDTO of(LeagueGameDTO.SubstitutionInfo info)
            {
                return new SubstitutionDTO(info.minute(), info.playerOutId(), info.playerOutName(), info.playerInId(), info.playerInName(), info.substitutionTeam());
            }
        }
    }
}

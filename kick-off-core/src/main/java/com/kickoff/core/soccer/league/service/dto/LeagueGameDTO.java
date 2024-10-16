package com.kickoff.core.soccer.league.service.dto;

import com.kickoff.core.soccer.game.*;
import com.kickoff.core.soccer.league.LeagueTeam;
import com.kickoff.core.soccer.team.Goal;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public record LeagueGameDTO(
        Long leagueGameId,
        LocalDateTime gameDate,
        int matchDay,
        LeagueTeamDTO away,
        LeagueTeamDTO home,
        ScoreDTO score,
        LeagueGameStatus leagueGameStatus,
        String seasonYear,
        String venue,
        String homeFormation,
        String awayFormation,
        List<LeagueGamePlayerDTO> homePlayers,
        List<LeagueGamePlayerDTO> awayPlayers,
        List<LeagueGamePlayerDTO> homeBenchPlayers,
        List<LeagueGamePlayerDTO> awayBenchPlayers,
        List<GoalInfo> goalInfos,
        List<SubstitutionInfo> substitutionInfos,
        Long minute,
        Long injuryTime,
        List<GameBookingDTO> gameBookingDTOS,
        GameStatisticsDTO homeGameStatisticsDTO,
        GameStatisticsDTO awayGameStatisticsDTO
) {
    public static LeagueGameDTO of(LeagueGame leagueGame) {
        // Substitutions 리스트를 로그로 출력

        return new LeagueGameDTO(
                leagueGame.getLeagueGameId(),
                leagueGame.getGameDate(),
                leagueGame.getMatchDay(),
                LeagueTeamDTO.of(leagueGame.getAway()),
                LeagueTeamDTO.of(leagueGame.getHome()),
                ScoreDTO.of(leagueGame.getScore()),
                leagueGame.getLeagueGameStatus(),
                leagueGame.getSeason().getYears(),
                leagueGame.getVenue(),
                leagueGame.getHomeFormation(),
                leagueGame.getAwayFormation(),
                makePlayers(leagueGame.getGameLineUps().stream().filter(s->s.getType().equals("home")).collect(Collectors.toList())), // TODO : 선수 추가 되면 변경
                makePlayers(leagueGame.getGameLineUps().stream().filter(s->s.getType().equals("away")).collect(Collectors.toList())),
                benchPlayers(leagueGame.getGameBenches().stream().filter(s->s.getType().equals("home")).collect(Collectors.toList())),
                benchPlayers(leagueGame.getGameBenches().stream().filter(s->s.getType().equals("away")).collect(Collectors.toList())),
                GoalInfo.of(leagueGame.getGoals()),
                SubstitutionInfo.of(leagueGame.getSubstitutionsList(),leagueGame.getHome(), leagueGame.getAway()),
                leagueGame.getMinute(),
                leagueGame.getInjuryTime(),
                leagueGame.getGameBookings().stream().map(GameBookingDTO::of).collect(Collectors.toList()),
                GameStatisticsDTO.of(leagueGame.getStatistics().stream().filter(s->s.getType().equals("home")).findFirst().orElse(null)),
                GameStatisticsDTO.of(leagueGame.getStatistics().stream().filter(s->s.getType().equals("away")).findFirst().orElse(null))
        );


    }

    private static List<LeagueGamePlayerDTO> makePlayers(List<GameLineUp> gameLineUps)
    {
        return gameLineUps.stream()
                .map(line -> new LeagueGamePlayerDTO(line.getPlayerId(), line.getPosition(), line.getShirtNumber(), line.getPlayerKrName(), line.getPlayerEnName()))
                .collect(Collectors.toList());
    }

    private static List<LeagueGamePlayerDTO> benchPlayers(List<Bench> gameBenches)
    {
        return gameBenches.stream()
                .map(line -> new LeagueGamePlayerDTO(line.getPlayerId(), line.getPosition(), line.getShirtNumber(), line.getPlayerKrName(), line.getPlayerEnName()))
                .collect(Collectors.toList());
    }

    // 성능 개선
    public record GoalInfo(Long playerId, String playerName, String goalType, int playTime, String teamName, Long teamId)
    {
        public static List<GoalInfo> of(List<Goal> goals)
        {
            return goals.stream()
                    .map(s-> new GoalInfo(
                            s.getPlayer().getPlayerId(),
                            s.getPlayer().getPlayerKrName(),
                            s.getType() == null ? "" : s.getType().name(),
                            s.getPlayTime(),
                            s.getScoredTeam().getLeagueTeamName(),
                            s.getScoredTeam().getLeagueTeamId()))
                    .toList();
        }
    }

    @Builder
    public record GameStatisticsDTO(
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
        public static GameStatisticsDTO of(GameStatistics statistics)
        {
            if (statistics ==  null)
            {
                return null;
            }
            return GameStatisticsDTO.builder()
                    .cornerKicks(statistics.getCornerKicks())
                    .freeKicks(statistics.getFreeKicks())
                    .goalKicks(statistics.getGoalKicks())
                    .offsides(statistics.getOffsides())
                    .fouls(statistics.getFouls())
                    .ballPossession(statistics.getBallPossession())
                    .saves(statistics.getSaves())
                    .throwIns(statistics.getThrowIns())
                    .shots(statistics.getShots())
                    .shotsOnGoal(statistics.getShotsOnGoal())
                    .shotsOffGoal(statistics.getShotsOffGoal())
                    .yellowCards(statistics.getYellowCards())
                    .yellowRedCards(statistics.getYellowRedCards())
                    .redCards(statistics.getRedCards())
                    .build();
        }
    }

    public record GameBookingDTO(
            Long minute,
            Long leagueTeamId,
            Long playerId,
            String type,
            String playerKrName,
            String playerEnName,
            String cardType
    ) {
        public static GameBookingDTO of(GameBooking gameBooking)
        {
            return new GameBookingDTO(
                    gameBooking.getMinute(),
                    gameBooking.getLeagueTeamId(),
                    gameBooking.getPlayerId(),
                    gameBooking.getType(),
                    gameBooking.getPlayerKrName(),
                    gameBooking.getPlayerEnName(),
                    gameBooking.getCardType()
            );
        }
    }

    public record SubstitutionInfo(int minute, Long playerOutId, String playerOutName,Long playerInId, String playerInName, String substitutionTeam)
    {
        public static List<SubstitutionInfo> of(List<Substitutions> substitutions, LeagueTeam homeTeam, LeagueTeam awayTeam)
        {
            return substitutions.stream()
                    .distinct()
                    .map(s->new SubstitutionInfo(
                            s.getMinute(),
                            s.getPlayerOut().getPlayerId(),
                            s.getPlayerOut().getPlayerKrName(),
                            s.getPlayerIn().getPlayerId(),
                            s.getPlayerIn().getPlayerKrName(),
                            s.getSubstitutionTeam().getLeagueTeamName().equals(homeTeam.getLeagueTeamName()) ? "home" : "away"
                    )).toList();
        }
    }

}

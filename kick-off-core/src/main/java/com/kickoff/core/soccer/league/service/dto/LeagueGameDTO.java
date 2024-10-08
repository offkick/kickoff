package com.kickoff.core.soccer.league.service.dto;

import com.kickoff.core.soccer.game.GameBooking;
import com.kickoff.core.soccer.game.GameLineUp;
import com.kickoff.core.soccer.game.LeagueGame;
import com.kickoff.core.soccer.game.LeagueGameStatus;
import com.kickoff.core.soccer.game.Substitutions;
import com.kickoff.core.soccer.league.LeagueTeam;
import com.kickoff.core.soccer.team.Goal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
        List<GoalInfo> goalInfos,
        List<SubstitutionInfo> substitutionInfos,
        Long minute,
        Long injuryTime,
        List<GameBookingDTO> gameBookingDTOS
) {
    public static LeagueGameDTO of(LeagueGame leagueGame) {
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
                GoalInfo.of(leagueGame.getGoals()),
                SubstitutionInfo.of(leagueGame.getSubstitutionsList(),leagueGame.getHome(), leagueGame.getAway()),
                leagueGame.getMinute(),
                leagueGame.getInjuryTime(),
                leagueGame.getGameBookings().stream().map(GameBookingDTO::of).collect(Collectors.toList())
        );
    }

    private static List<LeagueGamePlayerDTO> makePlayers(List<GameLineUp> gameLineUps)
    {
        return gameLineUps.stream()
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

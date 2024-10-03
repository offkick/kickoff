package com.kickoff.core.soccer.league.service.dto;

import com.kickoff.core.soccer.game.GameLineUp;
import com.kickoff.core.soccer.game.LeagueGame;
import com.kickoff.core.soccer.game.LeagueGameStatus;
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
        List<LeagueGamePlayerDTO> homePlayers,
        List<LeagueGamePlayerDTO> awayPlayers,
        List<GoalInfo> goalInfos,
        Long minute,
        Long injuryTime
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
                makePlayers(leagueGame.getGameLineUps().stream().filter(s->s.getType().equals("home")).collect(Collectors.toList())), // TODO : 선수 추가 되면 변경
                makePlayers(leagueGame.getGameLineUps().stream().filter(s->s.getType().equals("away")).collect(Collectors.toList())),
                GoalInfo.of(leagueGame.getGoals()),
                leagueGame.getMinute(),
                leagueGame.getInjuryTime()
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
}

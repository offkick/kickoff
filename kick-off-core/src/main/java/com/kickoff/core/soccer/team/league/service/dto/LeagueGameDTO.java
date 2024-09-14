package com.kickoff.core.soccer.team.league.service.dto;

import com.kickoff.core.soccer.team.Goal;
import com.kickoff.core.soccer.team.league.game.LeagueGame;
import com.kickoff.core.soccer.team.league.game.LeagueGameStatus;

import java.time.LocalDateTime;
import java.util.List;

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
        List<GoalInfo> goalInfos
) {
    public static LeagueGameDTO of(LeagueGame leagueGame) {
        System.out.println("#@!#*I(!#(*_)!#(*)");
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
                null, // TODO : 선수 추가 되면 변경
                null,
                GoalInfo.of(leagueGame.getGoals())
        );
    }

    // 성능 개선
    public record GoalInfo(Long playerId, String playerName, String goalType, int playTime, String teamName, Long teamId)
    {
        public static List<GoalInfo> of(List<Goal> goals)
        {
            System.out.println("WQEIUWQEEWQEWQEQEQ");
            return goals.stream()
                    .map(s-> new GoalInfo(
                            s.getPlayer().getPlayerId(),
                            s.getPlayer().getPlayerName(),
                            s.getType() == null ? "" : s.getType().name(),
                            s.getPlayTime(),
                            s.getScoredTeam().getLeagueTeamName(),
                            s.getScoredTeam().getLeagueTeamId()))
                    .toList();
        }
    }
}

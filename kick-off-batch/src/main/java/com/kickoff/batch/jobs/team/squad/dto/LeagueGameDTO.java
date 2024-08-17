package com.kickoff.batch.jobs.team.squad.dto;

import com.kickoff.core.soccer.team.league.game.LeagueGame;
import com.kickoff.core.soccer.team.league.game.LeagueGameStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LeagueGameDTO {
    private Long leagueGameId;
    private LocalDateTime gameDate;
    private int count;
    private Long awayLeagueTeamId;
    private Long homeLeagueTeamId;
    private String homeScore;
    private String awayScore;
    private LeagueGameStatus leagueGameStatus;
    private Long seasonId;

    public static LeagueGameDTO of(LeagueGame leagueGame)
    {
        return LeagueGameDTO.builder()
                .leagueGameId(leagueGame.getLeagueGameId())
                .gameDate(leagueGame.getGameDate())
                .count(leagueGame.getCount())
                .awayLeagueTeamId(leagueGame.getAway().getLeagueTeamId())
                .homeLeagueTeamId(leagueGame.getHome().getLeagueTeamId())
                .homeScore(leagueGame.getScore().getHomeScore())
                .awayScore(leagueGame.getScore().getAwayScore())
                .leagueGameStatus(leagueGame.getLeagueGameStatus())
                .seasonId(leagueGame.getSeason().getSeasonId())
                .build();
    }
}

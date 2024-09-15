package com.kickoff.core.soccer.league;

import com.kickoff.core.soccer.league.game.LeagueGame;
import com.kickoff.core.soccer.league.game.LeagueGameStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class LeagueGameStatic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leagueGameStaticId;

    private LocalDateTime gameDate;

    private int count;

    private Long awayLeagueTeamId;

    private Long homeLeagueTeamId;

    private String homeScore;

    private String awayScore;

    private LeagueGameStatus leagueGameStatus;

    private Long seasonId;

    @Builder
    public LeagueGameStatic(Long leagueGameStaticId, LocalDateTime gameDate, int count, Long awayLeagueTeamId, Long homeLeagueTeamId, String homeScore, String awayScore, LeagueGameStatus leagueGameStatus, Long seasonId) {
        this.leagueGameStaticId = leagueGameStaticId;
        this.gameDate = gameDate;
        this.count = count;
        this.awayLeagueTeamId = awayLeagueTeamId;
        this.homeLeagueTeamId = homeLeagueTeamId;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.leagueGameStatus = leagueGameStatus;
        this.seasonId = seasonId;
    }

    public static LeagueGameStatic of(LeagueGame leagueGame)
    {
        return LeagueGameStatic.builder()
                .leagueGameStaticId(leagueGame.getLeagueGameId())
                .gameDate(leagueGame.getGameDate())
                .count(leagueGame.getMatchDay())
                .awayLeagueTeamId(leagueGame.getAway().getLeagueTeamId())
                .homeLeagueTeamId(leagueGame.getHome().getLeagueTeamId())
                .homeScore(leagueGame.getScore().getHomeScore())
                .awayScore(leagueGame.getScore().getAwayScore())
                .leagueGameStatus(leagueGame.getLeagueGameStatus())
                .seasonId(leagueGame.getSeason().getSeasonId())
                .build();
    }
}

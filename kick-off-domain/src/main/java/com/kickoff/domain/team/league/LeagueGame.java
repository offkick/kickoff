package com.kickoff.domain.team.league;

import com.kickoff.domain.team.Score;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class LeagueGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leagueGameId;

    private LocalDateTime gameDate;
    private int count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "away_team_id")
    private LeagueTeam away;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_team_id")
    private LeagueTeam home;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "score_id")
    private Score score;

    @Enumerated(EnumType.STRING)
    private LeagueGameStatus leagueGameStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id")
    private Season season;
}

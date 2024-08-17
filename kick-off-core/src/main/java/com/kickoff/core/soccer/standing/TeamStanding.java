package com.kickoff.core.soccer.standing;

import com.kickoff.core.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "team_standing", indexes = @Index(name = "idx_team_standing", columnList = "season"))
@Entity
@Getter
@NoArgsConstructor
public class TeamStanding extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamStandingId;

    private Long teamId;
    private Long round;
    private Integer ranks;
    private Integer won;
    private Integer draw;
    private Integer lost;
    private Integer points;
    private Integer goalsFor;
    private Integer goalsAgainst;
    private String season;

    @Builder
    public TeamStanding(Long teamStandingId, Long teamId, Long round, int rank, int won, int draw, int lost, int points, int goalsFor, int goalsAgainst, String season) {
        this.teamStandingId = teamStandingId;
        this.teamId = teamId;
        this.round = round;
        this.ranks = rank;
        this.won = won;
        this.draw = draw;
        this.lost = lost;
        this.points = points;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
        this.season = season;
    }
}

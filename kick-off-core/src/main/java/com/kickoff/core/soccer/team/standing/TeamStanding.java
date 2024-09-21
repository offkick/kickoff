package com.kickoff.core.soccer.team.standing;

import com.kickoff.core.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "team_standing", indexes = @Index(name = "idx_team_standing", columnList = "season"))
@Entity
@Getter
@AllArgsConstructor
@Builder
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
    private Long leagueId;
    private String season;

    public TeamStanding update(TeamStanding build)
    {
        this.won=build.getWon();
        this.draw= build.getDraw();
        this.lost = build.lost;
        this.points = build.getPoints();
        this.goalsFor = build.getGoalsFor();
        this.goalsAgainst = build.goalsAgainst;
        this.round = build.getRound();
        return this;
    }
}

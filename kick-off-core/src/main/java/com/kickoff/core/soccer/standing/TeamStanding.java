package com.kickoff.core.soccer.standing;

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
    private String season;
}

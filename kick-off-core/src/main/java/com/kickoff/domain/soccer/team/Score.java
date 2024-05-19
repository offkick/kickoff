package com.kickoff.domain.soccer.team;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scoreId;

    private String homeScore;
    private String awayScore;

    @Builder
    public Score(Long scoreId, String homeScore, String awayScore) {
        this.scoreId = scoreId;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }
}

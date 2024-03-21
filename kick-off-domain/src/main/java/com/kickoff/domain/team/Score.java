package com.kickoff.domain.team;

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

    private int homeScore;
    private int awayScore;

    @Builder
    public Score(Long scoreId, int homeScore, int awayScore) {
        this.scoreId = scoreId;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }
}

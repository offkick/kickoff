package com.kickoff.core.soccer.team;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class Score {
    private String homeScore;
    private String awayScore;

    @Builder
    public Score(String homeScore, String awayScore)
    {
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }
}

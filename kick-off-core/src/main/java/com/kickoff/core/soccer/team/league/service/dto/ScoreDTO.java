package com.kickoff.core.soccer.team.league.service.dto;

import com.kickoff.core.soccer.team.Score;

public record ScoreDTO(
        String homeScore,
        String awayScore
) {
    public static ScoreDTO of(Score score)
    {
        if (score == null)
        {
            return new ScoreDTO(null, null);
        }
        return new ScoreDTO(score.getHomeScore(), score.getAwayScore());
    }
}

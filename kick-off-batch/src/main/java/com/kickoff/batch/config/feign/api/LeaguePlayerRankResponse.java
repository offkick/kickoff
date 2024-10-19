package com.kickoff.batch.config.feign.api;

import java.util.List;

public record LeaguePlayerRankResponse(
       List<LeaguePlayerRankDTO> scorers
) {

    public record LeaguePlayerRankDTO(
            Team team,
            Scorers player,
            Long playedMatches,
            Long goals,
            Long assists,
            Long penalties
    ) {
    }

}

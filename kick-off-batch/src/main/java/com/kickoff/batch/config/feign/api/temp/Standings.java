package com.kickoff.batch.config.feign.api.temp;

import java.util.List;

public record Standings(
        String stage,
        String type,
        String group,
        List<Table> table
) {
    public record Table(
        int position,
        Team team,
        int playedGames,
        String form,
        int won,
        int draw,
        int lost,
        int points,
        int goalsFor,
        int goalsAgainst,
        int goalDifference
    ) {
    }
}

package com.kickoff.core.soccer.standing.dto;

public record TeamStandingQueryResult(
        Long teamId,
        Long round,
        Integer ranks,
        Integer won,
        Integer draw,
        Integer lost,
        Integer points,
        Integer goalsFor,
        Integer goalsAgainst,
        String season,
        String teamName
) {
}

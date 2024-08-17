package com.kickoff.core.soccer.team.standing.dto;

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

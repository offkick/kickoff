package com.kickoff.core.soccer.league.service.dto;

public record LeagueGamePlayerDTO(
        Long playerId,
        String position,
        String shirtNumber,
        String playerKrName,
        String playerEnName
) {
}

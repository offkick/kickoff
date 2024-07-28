package com.kickoff.admin.service.dto;

public record SearchPlayerRequest(
        String playerName,
        String national,
        Long leagueTeamId
) {
}

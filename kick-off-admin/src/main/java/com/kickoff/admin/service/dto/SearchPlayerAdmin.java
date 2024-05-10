package com.kickoff.admin.service.dto;

public record SearchPlayerAdmin(
        String playerName,
        String national,
        Long leagueTeamId
) {
}

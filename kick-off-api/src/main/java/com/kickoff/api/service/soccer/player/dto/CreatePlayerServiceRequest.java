package com.kickoff.api.service.soccer.player.dto;

import com.kickoff.domain.soccer.player.PlayerPosition;

public record CreatePlayerServiceRequest(
        String national,
        String playerName,
        PlayerPosition playerPosition,
        Long leagueTeamId


) {
}

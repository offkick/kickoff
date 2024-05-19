package com.kickoff.domain.soccer.player.service.dto;

import com.kickoff.domain.soccer.player.PlayerPosition;

public record CreatePlayerRequest(
        String national,
        String playerName,
        PlayerPosition playerPosition,
        Long leagueTeamId
) {
}

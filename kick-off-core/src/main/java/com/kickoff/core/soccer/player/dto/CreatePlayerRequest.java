package com.kickoff.core.soccer.player.dto;

import com.kickoff.core.soccer.player.PlayerPosition;

public record CreatePlayerRequest(
        String national,
        String playerName,
        PlayerPosition playerPosition,
        Long leagueTeamId
) {
}

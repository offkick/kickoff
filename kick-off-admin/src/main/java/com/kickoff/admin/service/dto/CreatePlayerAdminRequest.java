package com.kickoff.admin.service.dto;


import com.kickoff.core.soccer.player.PlayerPosition;

public record CreatePlayerAdminRequest(
        String national,
        String playerName,
        PlayerPosition playerPosition,
        Long leagueTeamId

) {
}

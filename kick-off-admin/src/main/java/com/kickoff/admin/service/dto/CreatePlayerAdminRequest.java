package com.kickoff.admin.service.dto;

import com.kickoff.domain.soccer.player.PlayerPosition;
import com.kickoff.domain.soccer.team.league.LeagueTeam;

public record CreatePlayerAdminRequest(
        String national,
        String playerName,
        PlayerPosition playerPosition,
        LeagueTeam leagueTeam
) {
}

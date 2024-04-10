package com.kickoff.api.service.player.dto;

import com.kickoff.domain.player.PlayerPosition;
import com.kickoff.domain.team.league.LeagueTeam;

public record CreatePlayerServiceRequest(
        String national,
        String playerName,
        PlayerPosition playerPosition,
        Long leagueTeamId


) {
}

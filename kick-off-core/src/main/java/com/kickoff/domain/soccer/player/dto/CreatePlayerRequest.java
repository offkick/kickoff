package com.kickoff.domain.soccer.player.dto;

import com.kickoff.domain.soccer.player.PlayerPosition;
import com.kickoff.domain.soccer.team.league.LeagueTeam;
import lombok.Getter;

public record CreatePlayerRequest(
        String national,
        String playerName,
        PlayerPosition playerPosition,
        Long leagueTeamId
) {
}

package com.kickoff.api.controller.soccer.player.dto;

import com.kickoff.core.soccer.player.PlayerPosition;
import com.kickoff.core.soccer.player.dto.PlayerDTO;

public record FindPlayerApiResponse(
    Long playerId,
    String national,
    String playerName,
    PlayerPosition position,
    String leagueTeamName,
    Long leagueTeamId
) {
    public static FindPlayerApiResponse from(PlayerDTO response){
        return new FindPlayerApiResponse(
                response.playerId(),
                response.national(),
                response.playerName(),
                response.position(),
                response.leagueTeamName(),
                response.leagueTeamId()
        );
    }
}

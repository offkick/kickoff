package com.kickoff.api.controller.soccer.player.dto;

import com.kickoff.core.soccer.player.PlayerPosition;

public record FindPlayerResponse(
    Long playerId,
    String national,
    String playerName,
    PlayerPosition position,
    String leagueTeamName,
    Long leagueTeamId
) {
    public static FindPlayerResponse from(com.kickoff.core.soccer.player.dto.FindPlayerResponse response){
        return new FindPlayerResponse(
                response.playerId(),
                response.national(),
                response.playerName(),
                response.position(),
                response.leagueTeamName(),
                response.leagueTeamId()
        );
    }
}

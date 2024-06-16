package com.kickoff.api.controller.soccer.player.dto;

import com.kickoff.core.soccer.player.PlayerPosition;
import com.kickoff.core.soccer.player.dto.FindPlayerResponse;

public record FindPlayerResponseDto(
        Long playerId,
        String national,
        String playerName,
        PlayerPosition position,
        String leagueTeamName,
        Long leagueTeamId

) {
    public static FindPlayerResponseDto from(FindPlayerResponse response){
        return new FindPlayerResponseDto(
                response.playerId(),
                response.national(),
                response.playerName(),
                response.position(),
                response.leagueTeamName(),
                response.leagueTeamId()
        );
    }
}

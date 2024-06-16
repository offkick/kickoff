package com.kickoff.core.soccer.player.dto;

import com.kickoff.core.soccer.player.Player;
import com.kickoff.core.soccer.player.PlayerPosition;

public record FindPlayerResponse(
        Long playerId,
        String national,
        String playerName,
        PlayerPosition position,
        String leagueTeamName,
        Long leagueTeamId
) {
    public static FindPlayerResponse from(Player player){
        return new FindPlayerResponse(
                player.getPlayerId(),
                player.getNational(),
                player.getPlayerName(),
                player.getPosition(),
                player.getLeagueTeam().getLeagueTeamName(),
                player.getLeagueTeam().getLeagueTeamId()
        );
    }
}

package com.kickoff.core.soccer.player.dto;

import com.kickoff.core.soccer.player.Player;
import com.kickoff.core.soccer.player.PlayerPosition;
import com.kickoff.core.soccer.team.league.LeagueTeam;

public record FindPlayerResponse(
        Long playerId,
        String national,
        String playerName,
        PlayerPosition position,
        LeagueTeam leagueTeam
) {
    public static FindPlayerResponse from(Player player){
        return new FindPlayerResponse(
                player.getPlayerId(),
                player.getNational(),
                player.getPlayerName(),
                player.getPosition(),
                player.getLeagueTeam()
        );
    }
}

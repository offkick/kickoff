package com.kickoff.admin.service.dto;

import com.kickoff.core.soccer.player.Player;
import com.kickoff.core.soccer.player.PlayerImage;
import com.kickoff.core.soccer.player.PlayerPosition;
import com.kickoff.core.soccer.player.dto.PlayerDTO;

import java.util.Set;
import java.util.stream.Collectors;

public record FindPlayerResponses(
        Long playerId,
        String national,
        String playerName,
        PlayerPosition position,
        String leagueTeamName,
        Long leagueTeamId,
        Set<String> playerImages
) {
    public static PlayerDTO from(Player player){
        return new PlayerDTO(
                player.getPlayerId(),
                player.getNational(),
                player.getPlayerName(),
                player.getPosition(),
                player.getLeagueTeam().getLeagueTeamName(),
                player.getLeagueTeam().getLeagueTeamId(),
                player.getPlayerImages().stream().map(PlayerImage::getUrl).collect(Collectors.toSet())
        );
    }
}


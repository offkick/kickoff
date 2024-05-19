package com.kickoff.core.soccer.player.dto;


public record PlayerSearchCondition(
        String playerName,
        String national,
        Long leagueTeamId

) {

}

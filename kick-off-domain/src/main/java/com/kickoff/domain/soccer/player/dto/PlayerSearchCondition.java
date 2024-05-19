package com.kickoff.domain.soccer.player.dto;

import lombok.Getter;


public record PlayerSearchCondition(
        String playerName,
        String national,
        Long leagueTeamId

) {

}

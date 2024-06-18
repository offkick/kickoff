package com.kickoff.api.controller.team.league.dto;

import com.kickoff.core.soccer.player.PlayerPosition;

public record GamePlayer(
        String name,
        int playedTime,
        int subTime,
        PlayerPosition position
) {
}

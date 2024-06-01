package com.kickoff.core.soccer.team.league.game.dto;

import java.time.LocalDate;

public record GameSearchCondition(
        LocalDate startDate,
        LocalDate endDate,
        Long leagueId
) {

}

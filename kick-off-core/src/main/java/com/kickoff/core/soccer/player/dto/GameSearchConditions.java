package com.kickoff.core.soccer.player.dto;

import java.time.LocalDate;

public record GameSearchConditions(
        LocalDate startDate,
        LocalDate endDate,
        Long leagueId
) {
}

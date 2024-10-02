package com.kickoff.core.soccer.league.game.dto;

import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public record FindGameCond(
        LocalDate startDate,
        LocalDate endDate,
        Long leagueId,
        Long teamId, Pageable pageable
) {
}

package com.kickoff.core.soccer.team.league.game.dto;

import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public record FindGameCond(
        LocalDate startDate,
        LocalDate endDate,
        Long leagueId,
        Pageable pageable
) {
}

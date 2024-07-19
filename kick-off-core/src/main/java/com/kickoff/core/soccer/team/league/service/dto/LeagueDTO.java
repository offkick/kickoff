package com.kickoff.core.soccer.team.league.service.dto;

import com.kickoff.core.common.National;

public record LeagueDTO(
        Long leagueId,
        String leagueName,
        National national,
        String emblem,
        String tier,
        String seasonYear
) {}

package com.kickoff.api.service.soccer.team.league.dto;

import com.kickoff.core.common.National;

public record CreateLeagueServiceRequest(
        String leagueName,
        National national,
        String tier
) {
}

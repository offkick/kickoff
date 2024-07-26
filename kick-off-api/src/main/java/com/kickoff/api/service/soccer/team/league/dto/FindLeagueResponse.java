package com.kickoff.api.service.soccer.team.league.dto;

import com.kickoff.core.soccer.team.league.service.dto.LeagueDTO;

public record FindLeagueResponse(
        Long leagueId,
        String leagueName
) {
    public static FindLeagueResponse from(LeagueDTO league) {
        return new FindLeagueResponse(
                league.leagueId(),
                league.leagueName()
        );
    }
}

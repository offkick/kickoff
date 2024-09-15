package com.kickoff.api.service.soccer.team.league.dto;

import com.kickoff.core.soccer.league.service.dto.LeagueDTO;

public record FindLeagueResponse(
        Long leagueId,
        String leagueName,
        String season
) {
    public static FindLeagueResponse from(LeagueDTO league) {
        return new FindLeagueResponse(
                league.leagueId(),
                league.leagueName(),
                league.seasonYear()
        );
    }
}

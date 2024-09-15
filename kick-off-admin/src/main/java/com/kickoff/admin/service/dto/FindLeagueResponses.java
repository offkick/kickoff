package com.kickoff.admin.service.dto;

import com.kickoff.core.soccer.league.League;

public record FindLeagueResponses(
        Long leagueId,
        String leagueName

) {
    public static FindLeagueResponses of(League league)
    {
        return new FindLeagueResponses(
                league.getLeagueId(),
                league.getLeagueName()
        );
    }
}

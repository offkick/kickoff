package com.kickoff.core.soccer.league.dto;

import com.kickoff.core.soccer.league.League;

public record FindLeagueResponse(
        Long leagueId,
        String leagueName

) {
    public static FindLeagueResponse from(League league)
    {
        return new FindLeagueResponse(
                league.getLeagueId(),
                league.getLeagueName()
        );
    }
}

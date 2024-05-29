package com.kickoff.core.soccer.team.league.dto;

import com.kickoff.core.soccer.team.league.League;

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

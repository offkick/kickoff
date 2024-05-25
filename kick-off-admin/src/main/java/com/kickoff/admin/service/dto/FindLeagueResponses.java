package com.kickoff.admin.service.dto;

import com.kickoff.core.soccer.team.league.League;
import com.kickoff.core.soccer.team.league.dto.FindLeagueResponse;

public record FindLeagueResponses(
        Long leagueId,
        String leagueName

) {
    public static FindLeagueResponses from(League league)
    {
        return new FindLeagueResponses(
                league.getLeagueId(),
                league.getLeagueName()
        );
    }
}

package com.kickoff.api.service.soccer.team.league.dto;

import com.kickoff.core.soccer.team.league.League;
public record FindLeagueResponseDto(
        Long leagueId,
        String leagueName
) {
    public static FindLeagueResponseDto from(League league) {
        return new FindLeagueResponseDto(
                league.getLeagueId(),
                league.getLeagueName()
        );
    }
}

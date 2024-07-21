package com.kickoff.api.service.soccer.team.league.dto;

import com.kickoff.core.soccer.team.league.League;
import com.kickoff.core.soccer.team.league.service.dto.LeagueDTO;

public record FindLeagueResponseDto(
        Long leagueId,
        String leagueName
) {
    public static FindLeagueResponseDto from(LeagueDTO league) {
        return new FindLeagueResponseDto(
                league.leagueId(),
                league.leagueName()
        );
    }
}

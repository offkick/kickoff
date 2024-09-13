package com.kickoff.core.soccer.team.league.service.dto;

import com.kickoff.core.common.National;
import com.kickoff.core.soccer.team.league.League;

public record LeagueDTO(
        Long leagueId,
        String leagueName,
        National national,
        String emblem,
        String tier,
        String seasonYear,
        Long winner
) {
    public static LeagueDTO of(League league) {
        return new LeagueDTO(
                league.getLeagueId(),
                league.getLeagueName(),
                league.getNational(),
                league.getEmblem(),
                league.getTier(),
                league.getSeason().getYears(),
                league.getWinner()
        );
    }
}

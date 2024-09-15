package com.kickoff.core.soccer.league.service.dto;

import com.kickoff.core.common.National;
import com.kickoff.core.soccer.league.League;

public record LeagueDTO(
        Long leagueId,
        String leagueName,
        National national,
        String emblem,
        String tier,
        String seasonYear,
        Long winnerId
) {
    public static LeagueDTO of(League league) {
        return new LeagueDTO(
                league.getLeagueId(),
                league.getLeagueName(),
                league.getNational(),
                league.getEmblem(),
                league.getTier(),
                league.getSeason().getYears(),
                league.getWinnerId()
        );
    }
}

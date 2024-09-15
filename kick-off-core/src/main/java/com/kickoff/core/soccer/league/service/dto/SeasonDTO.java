package com.kickoff.core.soccer.league.service.dto;

import com.kickoff.core.soccer.league.Season;

public record SeasonDTO(String years) {
    public static SeasonDTO of(Season season)
    {
        return new SeasonDTO(season.getYears());
    }
}

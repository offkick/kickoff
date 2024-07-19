package com.kickoff.core.soccer.team.league.service.dto;

import com.kickoff.core.soccer.team.TeamType;
import com.kickoff.core.soccer.team.league.LeagueTeam;

public record LeagueTeamDTO(
        Long leagueTeamId,
        String leagueTeamName,
        TeamType teamType,
        LeagueDTO league,
        String logo
) {
    public static LeagueTeamDTO of(LeagueTeam entity) {
        return new LeagueTeamDTO(
                entity.getLeagueTeamId(),
                entity.getLeagueTeamName(),
                entity.getTeamType(),
                new LeagueDTO(
                        entity.getLeague().getLeagueId(),
                        entity.getLeagueTeamName(),
                        entity.getLeague().getNational(),
                        null,
                        entity.getLeague().getTier(),
                        new SeasonDTO(entity.getLeague().getSeason().getSeasonId(), entity.getLeague().getSeason().getYears())
                ),
                entity.getLogo()
        );
    }
}

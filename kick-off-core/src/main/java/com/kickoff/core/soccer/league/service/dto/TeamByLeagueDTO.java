package com.kickoff.core.soccer.league.service.dto;

import com.kickoff.core.soccer.league.LeagueTeam;
import com.kickoff.core.soccer.team.TeamType;

public record TeamByLeagueDTO(
        Long leagueTeamId,
        String leagueTeamName,
        TeamType teamType,
        String leagueName,
        String logo
) {
    public static TeamByLeagueDTO of(LeagueTeam leagueTeam)
    {
        return new TeamByLeagueDTO(
                leagueTeam.getLeagueTeamId(),
                leagueTeam.getLeagueTeamName(),
                leagueTeam.getTeamType(),
                leagueTeam.getLeague().getLeagueName(),
                leagueTeam.getLogo()
        );
    }
}
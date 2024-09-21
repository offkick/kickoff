package com.kickoff.api.service.soccer.team.league.dto;

import com.kickoff.core.soccer.league.service.dto.TeamByLeagueDTO;
import com.kickoff.core.soccer.team.TeamType;

public record LeagueTeamResponse(
        Long leagueTeamId,
        String leagueTeamName,
        TeamType teamType,
        String leagueName,
        String logo
) {
    public static LeagueTeamResponse of(TeamByLeagueDTO leagueTeam)
    {
        return new LeagueTeamResponse(
                leagueTeam.leagueTeamId(),
                leagueTeam.leagueTeamName(),
                leagueTeam.teamType(),
                leagueTeam.leagueName(),
                leagueTeam.logo()
        );
    }


}

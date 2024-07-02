package com.kickoff.admin.service.dto;

import com.kickoff.core.soccer.team.league.LeagueTeam;

public record LeagueTeamDto(
        Long leagueTeamId,
        String leagueTeamName

) {
    public static LeagueTeamDto from(LeagueTeam team)
    {
        return new LeagueTeamDto(
                team.getLeagueTeamId(),
                team.getLeagueTeamName()
        );
    }
}

package com.kickoff.admin.service.dto;

import com.kickoff.core.soccer.league.service.dto.LeagueTeamDTO;

public record LeagueTeamDto(
        Long leagueTeamId,
        String leagueTeamName

) {
    public static LeagueTeamDto from(LeagueTeamDTO team)
    {
        return new LeagueTeamDto(
                team.leagueTeamId(),
                team.leagueTeamName()
        );
    }
}

package com.kickoff.api.service.soccer.team.league.dto;

import com.kickoff.domain.soccer.team.TeamType;

public record CreateTeamServiceRequest(
        String leagueTeamName,
        TeamType teamType,
        Long leagueId

) {
}

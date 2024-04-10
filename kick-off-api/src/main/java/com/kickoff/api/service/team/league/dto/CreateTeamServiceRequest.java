package com.kickoff.api.service.team.league.dto;

import com.kickoff.domain.team.TeamType;

public record CreateTeamServiceRequest(
        String leagueTeamName,
        TeamType teamType,
        Long leagueId

) {
}

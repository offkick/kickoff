package com.kickoff.api.service.soccer.team.national.dto;

import com.kickoff.core.soccer.team.TeamType;

public record CreateNationalTeamServiceRequest(
        String nationalTeamName,
        TeamType teamType
) {
}

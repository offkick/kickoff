package com.kickoff.batch.config.feign.api.temp;

import com.kickoff.batch.config.feign.api.CompetitionTeamsResponse;

public record Squad(
        int id,
        String name,
        String position,
        String dateOfBirth,
        String nationality,
        CompetitionTeamsResponse.Contract contract
) {}
package com.kickoff.batch.config.feign.api.temp;

import java.util.List;

public record SeasonDTO(
        int id,
        String startDate,
        String endDate,
        int currentMatchday,
        Winner winner,
        List<String> stages
) {
}


package com.kickoff.batch.config.feign.api.temp;

import java.util.List;

public record Season(
        int id,
        String startDate,
        String endDate,
        int currentMatchday,
        String winner,
        List<String> stages
) {}

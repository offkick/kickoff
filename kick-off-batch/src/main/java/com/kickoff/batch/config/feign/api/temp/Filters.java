package com.kickoff.batch.config.feign.api.temp;

public record Filters(
        String dateFrom,
        String dateTo,
        String permission,
        String competitions
) {}
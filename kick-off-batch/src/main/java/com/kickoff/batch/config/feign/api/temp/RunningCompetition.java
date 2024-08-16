package com.kickoff.batch.config.feign.api.temp;

public record RunningCompetition(
        int id,
        String name,
        String code,
        String type,
        String emblem
) {}
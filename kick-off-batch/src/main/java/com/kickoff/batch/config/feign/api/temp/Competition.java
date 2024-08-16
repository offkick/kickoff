package com.kickoff.batch.config.feign.api.temp;

public record Competition(
        int id,
        String name,
        String code,
        String type,
        String emblem
) {}
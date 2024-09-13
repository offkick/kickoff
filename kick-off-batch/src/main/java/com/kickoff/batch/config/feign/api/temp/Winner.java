package com.kickoff.batch.config.feign.api.temp;

public record Winner(
        int id,
        String name,
        String shortName,
        String tla,
        String crest,
        String address,
        String website,
        String founded,
        String clubColors,
        String venue

) {}
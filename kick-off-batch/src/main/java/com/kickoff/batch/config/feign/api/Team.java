package com.kickoff.batch.config.feign.api;

public record Team(
    Long id,
    String name,
    String shortName,
    String tla,
    String crest,
    String address,
    String website,
    float founded,
    String clubColors,
    String venue,
    String lastUpdated
) {
}

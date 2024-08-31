package com.kickoff.batch.config.feign.api.temp;

import java.util.List;

public record Season(
        int id,
        String startDate,
        String endDate,
        int currentMatchday,
        Winner winner,
        List<String> stages
) {
    record Winner(
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
}

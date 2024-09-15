package com.kickoff.batch.config.feign.api.temp;

import java.util.List;

public record Competitions(
        Area area,
        Integer id,
        String name,
        String code,
        String type,
        String emblem,
        SeasonDTO currentSeason,
        List<SeasonDTO> seasons
) {
}

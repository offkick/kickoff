package com.kickoff.batch.config.feign.api.temp;

import java.util.List;

public record Competitions(
        Area area,
        Integer id,
        List<Season> seasons
) {
}

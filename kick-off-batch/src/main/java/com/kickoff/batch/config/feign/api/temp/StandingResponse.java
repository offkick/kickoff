package com.kickoff.batch.config.feign.api.temp;

import java.util.List;

public record StandingResponse(
        Filters filters,
        Area area,
        Competition competition,
        Season season,
        List<Standings> standings
) {
}

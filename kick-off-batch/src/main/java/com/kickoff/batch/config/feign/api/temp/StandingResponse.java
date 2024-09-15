package com.kickoff.batch.config.feign.api.temp;

import java.util.List;

public record StandingResponse(
        Filters filters,
        Area area,
        Competition competition,
        SeasonDTO season,
        List<Standings> standings
) {
}

package com.kickoff.batch.config.feign.api.temp;

import java.util.Date;
import java.util.List;

public record Team(
        Area area,
        int id,
        String name,
        String shortName,
        String tla,
        String crest,
        String address,
        String website,
        int founded,
        String formation,
        String clubColors,
        String venue,
        List<RunningCompetition> runningCompetitions,
        List<Object> staff,
        List<Squad> squad,
        Date lastUpdated,
        List<LineUp> lineup,
        List<LineUp> bench
) {}
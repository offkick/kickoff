package com.kickoff.batch.config.feign.api;


import com.kickoff.batch.config.feign.api.temp.Team;

import java.util.List;

public record CompetitionTeamsResponse(
        int count,
        Competition competition,
        Season season,
        List<Team> teams
) {
    public record Area(
            int id,
            String name,
            String code,
            String flag
    ) {}

    public record Competition(
            int id,
            String name,
            String code,
            String type,
            String emblem
    ) {}

    record Season(
            int id,
            String startDate,
            String endDate,
            int currentMatchday,
            Object winner
    ) {}

    record RunningCompetition(
            int id,
            String name,
            String code,
            String type,
            String emblem
    ) {}

    public record Squad(
            int id,
            String name,
            String position,
            String dateOfBirth,
            String nationality,
            Contract contract
    ) {}

    public record Contract(
            String start,
            String until
    ) {}

}

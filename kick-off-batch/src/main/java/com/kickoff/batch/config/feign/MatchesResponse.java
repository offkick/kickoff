package com.kickoff.batch.config.feign;

import java.util.Date;
import java.util.List;

public record MatchesResponse(
        Competition competition,
        ResultSet resultSet,
        Filters filters,
        List<Match> matches
) {
    record Competition(String id, String name, String code, String type, String emblem) {}
    record ResultSet(int count, String first, String last, int played) {}
    record Team(int id, String name, String shortName, String tla, String crest) {}
    record Filters(String season) {}
    record Area(int id, String name, String code, String flag) {}
    record GameTime(int home, int away) {}
    record HalfTime(int home, int away) {}
    record Odds(String msg) {}
    record Referee(int id, String name, String type, String nationality) {}
    record Season(
            int id,
            String startDate,
            String endDate,
            int currentMatchday,
            String winner
    ) {}
    record Match(Area area,
            Competition competition,
            Season season,
            Integer id,
            Date utcDate,
            String status,
            Integer matchday,
            String stage,
            String group,
            Date lastUpdated,
            Team homeTeam,
            Team awayTeam,
            Score score,
            Odds odds,
            List<Referee> referees
    ) {}
    record Score(
            String winner,
            String duration,
            GameTime gameTime,
            HalfTime halfTime
    ) {}
}


package com.kickoff.batch.config.feign.api;

import java.util.Date;
import java.util.List;

public record MatchResultResponse(
        Filters filters,
        ResultSet resultSet,
        List<Match> matches
) {
    public record Area(int id, String name, String code, String flag) {}
    public record Team(int id, String name, String shortName, String tla, String crest,List<Player> lineup) {}
    public record Competition(String id, String name, String code, String type, String emblem) {}
    public record Referee(int id, String name, String type, String nationality) {}
    public record GameTime(String home, String away) {}
    public record HalfTime(int home, int away) {}

    public record Player(int id, String name, String position) {}

    public record Match(Area area,
                        Competition competition,
                        Season season,
                        Integer id,
                        Date utcDate,
                        String status,
                        int matchday,
                        String stage,
                        String group,
                        Date lastUpdated,
                        Team homeTeam,
                        Team awayTeam,
                        String venue,
                        Score score,
                        Odds odds,
                        List<Referee> referees
    ) {}

    public record Score(
            String winner,
            String duration,
            MatchesFeignResponse.FullTime fullTime,
            MatchesFeignResponse.HalfTime halfTime
    ) {}

    public record Season(
            int id,
            String startDate,
            String endDate,
            int currentMatchday,
            String winner
    ) {}

    public record Filters(
            String dateFrom,
            String dateTo,
            String permission,
            String competitions
    ) {}

    public record Odds(String msg){}

    public record ResultSet(
            Integer count,
            String competitions,
            String first,
            String last,
            Integer played
    ) {}

    public record Winner(
            int id,
            String name,
            String shortName,
            String tla,
            String crest,
            String address,
            String website,
            int founded,
            String clubColors,
            String venue,
            Date lastUpdated
    ) {}

}

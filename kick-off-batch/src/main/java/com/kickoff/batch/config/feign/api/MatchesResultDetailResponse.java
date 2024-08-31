package com.kickoff.batch.config.feign.api;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public record MatchesResultDetailResponse(
        Competition competition,
        ResultSet resultSet,
        Filters filters,
        Match matches
) {
    record Competition(String id, String name, String code, String type, String emblem) {}
    record ResultSet(int count, String first, String last, int played) {}
    public record Team(int id, String name, String shortName, String tla, String crest, List<MatchResultResponse.Player> lineup, List<MatchResultResponse.Player> bench) {}
    record Filters(String season) {}
    record Area(int id, String name, String code, String flag) {}
    public record FullTime(String home, String away) {}
    public record HalfTime(int home, int away) {}
    record Odds(String msg) {}
    record Referee(int id, String name, String type, String nationality) {}

    record Season(
            int id,
            String startDate,
            String endDate,
            int currentMatchday,
            Winner winner
    ) {}

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


    public record Match(Area area
//                        Competition competition,
//                        Season season,
//                        Integer id,
//                        Date utcDate,
//                        String status,
//                        int matchTime,
//                        int injuryTime,
//                        int attendance,
//                        String venue,
//                        int matchday,
//                        String stage,
//                        String group,
//                        Date lastUpdated,
//                        Team homeTeam,
//                        Team awayTeam,
//                        Score score,
//                        List<Goals> goals,
//                        Penalties penalties,
//                        List<Bookings> bookings,
//                        List<Substitutions> substitutions,
//                        Odds odds,
//                        List<Referee> referees
    ) {}

    public record Penalties(
            Player player,
            PenaltyTeam penaltyTeam
    ){}

    public record Goals(
            int minute,
            int injuryTime,
            String type,
            DetailTeam scoreTeam,
            Scorer scorer,
            Player assist
    ){}
   public record Substitutions(
            int minute,
            DetailTeam team,
            Player playerOut,
            Player playerIn

    ){}

    public record Bookings(
            int minute,
            DetailTeam team,
            Player player,
            String card

    ){}

    public record Player(
            Integer id,
            String name
    ){}

    public record PenaltyTeam(
            Integer id,
            String name
    ){}
    public record Scorer(
            Long id,
            String name
    ){}
    public record DetailTeam(
            Integer id,
            String name
    ){}
    public record Score(
            String winner,
            String duration,
            FullTime fullTime,
            HalfTime halfTime
    ) {}
}



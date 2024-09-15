package com.kickoff.batch.config.feign.api.temp;

import java.util.Date;
import java.util.List;

public record Match(Area area,
                        Competition competition,
                        SeasonDTO season,
                        Integer id,
                        Date utcDate,
                        String status,
                        int matchTime,
                        int injuryTime,
                        int attendance,
                        String venue,
                        int matchday,
                        String stage,
                        String group,
                        Date lastUpdated,
                        Team homeTeam,
                        Team awayTeam,
                        Score score,
                        List<Goals> goals,
                        List<Penalties> penalties,
                        List<Bookings> bookings,
                        List<Substitutions> substitutions,
                        Odds odds,
                        List<Referee> referees
) {
    record Odds(String msg) {}
    record Referee(int id, String name, String type, String nationality) {}
    public record Penalties(
            Player player,
            PenaltyTeam penaltyTeam
    ){
        public record PenaltyTeam(
                Integer id,
                String name
        ){}
    }

    public record Substitutions(
            int minute,
            Bookings.DetailTeam team,
            Player playerOut,
            Player playerIn

    ){}
}
package com.kickoff.batch.config.feign.api.temp;

import com.kickoff.batch.config.feign.api.MatchesResultDetailResponse;

public record Bookings(
        int minute,
        DetailTeam team,
        Player player,
        String card

){
    public record DetailTeam(
            Integer id,
            String name
    ){}
}
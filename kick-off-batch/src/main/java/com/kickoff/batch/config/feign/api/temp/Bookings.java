package com.kickoff.batch.config.feign.api.temp;

public record Bookings(
        long minute,
        DetailTeam team,
        Player player,
        String card

){
    public record DetailTeam(
            Integer id,
            String name
    ){}
}
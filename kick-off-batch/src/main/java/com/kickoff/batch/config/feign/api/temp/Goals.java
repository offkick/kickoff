package com.kickoff.batch.config.feign.api.temp;

import com.kickoff.batch.config.feign.api.MatchesResultDetailResponse;

public record Goals(
        int minute,
        int injuryTime,
        String type,
        DetailTeam team,
        Scorer scorer,
        Player assist
){
    public record Scorer(
            Long id,
            String name
    ){}
    public record DetailTeam(
            Integer id,
            String name
    ){}
}

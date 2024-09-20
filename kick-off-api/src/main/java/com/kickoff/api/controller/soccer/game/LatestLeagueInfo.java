package com.kickoff.api.controller.soccer.game;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LatestLeagueInfo {
    private String leagueName;
    private Long leagueId;

    public LatestLeagueInfo(String leagueName, Long leagueId) {
        this.leagueName = leagueName;
        this.leagueId = leagueId;
    }
}

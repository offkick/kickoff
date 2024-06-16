package com.kickoff.api.elasticsearch.index;

public record SoccerPost(
        String leagueName,
        String leagueTeamName,
        String playerName
) {}

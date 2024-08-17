package com.kickoff.api.controller.team.league.dto;

import com.kickoff.core.soccer.standing.dto.TeamStandingQueryResult;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public record TeamRankInfo(
        List<TeamInfo> ranks
) {
    public static TeamRankInfo of(List<TeamStandingQueryResult> teamStandings)
    {
        return new TeamRankInfo(teamStandings.stream()
                .map(TeamInfo::of)
                .sorted(Comparator.comparingInt(a -> a.rank))
                .collect(Collectors.toList()));
    }

    public record TeamInfo(
            Long teamId,
            String teamName,
            String season,
            Integer rank,
            Integer won,
            Integer lost,
            Integer draw,
            Integer points,
            Integer goalsFor,
            Integer goalsAgainst
    ) {
        public static TeamInfo of(TeamStandingQueryResult teamStanding)
        {
            return new TeamInfo(
                    teamStanding.teamId(),
                    teamStanding.teamName(),
                    teamStanding.season(),
                    teamStanding.ranks(),
                    teamStanding.won(),
                    teamStanding.lost(),
                    teamStanding.draw(),
                    teamStanding.points(),
                    teamStanding.goalsFor(),
                    teamStanding.goalsAgainst()
            );
        }
    }
}

package com.kickoff.api.controller.team.league.dto;

import com.kickoff.core.soccer.standing.TeamStanding;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public record TeamRankInfo(
        List<TeamInfo> ranks
) {
    public static TeamRankInfo of(List<TeamStanding> teamStandings)
    {
        return new TeamRankInfo(teamStandings.stream()
                .map(TeamInfo::of)
                .sorted(Comparator.comparingInt(a -> a.rank))
                .collect(Collectors.toList()));
    }

    public record TeamInfo(
            Long teamId,
            String teamName,
            Integer rank,
            Integer won,
            Integer lost,
            Integer draw,
            Integer points,
            Integer goalsFor,
            Integer goalsAgainst
    ) {
        public static TeamInfo of(TeamStanding teamStanding)
        {
            return new TeamInfo(
                    teamStanding.getTeamId(),
                    teamStanding.getSeason(),
                    teamStanding.getRanks(),
                    teamStanding.getWon(),
                    teamStanding.getLost(),
                    teamStanding.getDraw(),
                    teamStanding.getPoints(),
                    teamStanding.getGoalsFor(),
                    teamStanding.getGoalsAgainst()
            );
        }
    }
}

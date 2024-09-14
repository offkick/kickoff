package com.kickoff.api.controller.soccer.league.dto;

import com.kickoff.core.soccer.team.standing.dto.TeamStandingQueryResult;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public record TeamRankInfo(
        List<MatchDayTeamInfo> rankInfo
) {
    public static TeamRankInfo of(List<TeamStandingQueryResult> teamStandings)
    {
        List<MatchDayTeamInfo> rankInfo = teamStandings.stream()
                .collect(Collectors.groupingBy(TeamStandingQueryResult::round))
                .entrySet()
                .stream()
                .map(entry -> {
                    List<TeamInfo> teamInfos = entry.getValue().stream()
                            .map(TeamInfo::of)
                            .collect(Collectors.toList());

                    return new MatchDayTeamInfo(entry.getKey(), teamInfos);
                })
                .sorted(Comparator.comparing(s->s.matchDay))
                .toList();

        return new TeamRankInfo(rankInfo);
    }

    public record MatchDayTeamInfo(
            Long matchDay,
            List<TeamInfo> ranks
    ) {}

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

package com.kickoff.core.soccer.league;


import com.kickoff.core.soccer.league.service.dto.TeamByLeagueDTO;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class LeagueTeamQuerydslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    QLeagueTeam leagueTeam = QLeagueTeam.leagueTeam;

    public List<TeamByLeagueDTO> findTeamByLeague(String years, Long leagueId)
    {
        List<LeagueTeam> teams = jpaQueryFactory.selectFrom(leagueTeam)
                .where(
                        leagueIdEq(leagueId),
                        yearsEq(years)
                )
                .fetch();

        return teams.stream()
                .map(TeamByLeagueDTO::of)
                .collect(Collectors.toList());

    }

    private BooleanExpression leagueIdEq(Long leagueId)
    {
        return leagueId != null ? leagueTeam.league.leagueId.eq(leagueId) : null;
    }

    private BooleanExpression yearsEq(String years)
    {
        return years != null ? leagueTeam.season.years.eq(years):null;
    }


}

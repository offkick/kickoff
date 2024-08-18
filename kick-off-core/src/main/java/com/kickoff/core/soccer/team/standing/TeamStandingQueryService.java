package com.kickoff.core.soccer.team.standing;

import com.kickoff.core.soccer.team.league.QLeagueTeam;
import com.kickoff.core.soccer.team.league.service.LeagueService;
import com.kickoff.core.soccer.team.league.service.dto.LeagueDTO;
import com.kickoff.core.soccer.team.standing.dto.TeamStandingQueryResult;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamStandingQueryService {
    private final JPAQueryFactory jpaQueryFactory;
    private final LeagueService leagueService;
    private final TeamStandingRepository teamStandingRepository;

    @Transactional(readOnly = true)
    public List<TeamStandingQueryResult> teamStandings(Long matchDay, String season, Long leagueId)
    {
        QTeamStanding teamStanding = QTeamStanding.teamStanding;
        return jpaQueryFactory.select(Projections.constructor(
                TeamStandingQueryResult.class,
                        teamStanding.teamId,
                        teamStanding.round,
                        teamStanding.ranks,
                        teamStanding.won,
                        teamStanding.draw,
                        teamStanding.lost,
                        teamStanding.points,
                        teamStanding.goalsFor,
                        teamStanding.goalsAgainst,
                        teamStanding.season,
                        QLeagueTeam.leagueTeam.leagueTeamName
                 ))
                .from(QTeamStanding.teamStanding)
                .join(QLeagueTeam.leagueTeam)
                .on(QLeagueTeam.leagueTeam.leagueTeamId.eq(teamStanding.teamId))
                .where(teamStanding.season.eq(season), eqMatchDay(season, matchDay), eqLeagueId(leagueId))
                .fetch();
    }

    private BooleanExpression eqLeagueId(Long leagueId)
    {
        if (leagueId == null)
        {
            // default league 는 프리미어리그
            LeagueDTO findLeague = leagueService.findByLeagueName("PL");
            return QTeamStanding.teamStanding.leagueId.eq(findLeague.leagueId());
        }

        return QTeamStanding.teamStanding.leagueId.eq(leagueId);
    }

    private BooleanExpression eqMatchDay(String season, Long matchDay)
    {
        if (matchDay == null)
        {
            Optional<TeamStanding> maxRound = teamStandingRepository.findBySeason(season).stream().max(Comparator.comparingLong(TeamStanding::getRound));

            if (maxRound.isEmpty())
            {
                throw new IllegalArgumentException("not found season match standings");
            }

            return QTeamStanding.teamStanding.round.eq(maxRound.get().getRound());
        }

        return QTeamStanding.teamStanding.round.eq(matchDay);
    }
}

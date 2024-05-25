package com.kickoff.core.soccer.team.league.game;

import com.kickoff.core.soccer.team.league.game.dto.FindLeagueGameResponse;
import com.kickoff.core.soccer.team.league.game.dto.GameSearchCondition;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.kickoff.core.soccer.team.league.game.QLeagueGame.leagueGame;

@Repository
@RequiredArgsConstructor
public class LeagueGameRepositoryImpl implements LeagueGameRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<FindLeagueGameResponse> searchGame(GameSearchCondition condition, Pageable pageable)
    {
        QLeagueGame leagueGame = QLeagueGame.leagueGame;
        QueryResults<LeagueGame> results = jpaQueryFactory.selectFrom(leagueGame)
                .where(gameDateEq(condition.startDate(), condition.endDate()),
                        leagueIdEq(condition.leagueId())
                ).offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<LeagueGame> content = results.getResults();
        long total = results.getTotal();
        List<FindLeagueGameResponse> responses = content.stream()
                .map(FindLeagueGameResponse::from)
                .collect(Collectors.toList());
        return new PageImpl<>(responses,pageable,total);


    }
    private BooleanExpression gameDateEq(LocalDate startDate, LocalDate endDate)
    {
        if (startDate != null && endDate != null)
        {
            return QLeagueGame.leagueGame.gameDate.between(startDate.atStartOfDay(), endDate.atTime(23, 59, 59));
        } else if (startDate != null)
        {
            return QLeagueGame.leagueGame.gameDate.goe(startDate.atStartOfDay());
        } else if (endDate != null)
        {
            return QLeagueGame.leagueGame.gameDate.loe(endDate.atTime(23, 59, 59));
        } else
        {
            return null;
        }
    }

    private BooleanExpression leagueIdEq(Long leagueId)
    {
        return leagueId != null ? leagueGame.away.league.leagueId.eq(leagueId) : null;

    }
}

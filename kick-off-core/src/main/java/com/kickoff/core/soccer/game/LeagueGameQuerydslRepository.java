package com.kickoff.core.soccer.game;

import com.kickoff.core.soccer.game.dto.FindLeagueGameResponse;
import com.kickoff.core.soccer.game.dto.FindGameCond;
import com.kickoff.core.soccer.game.dto.FindLeagueGamesResponse;
import com.kickoff.core.soccer.game.dto.GameSearchCondition;
import com.kickoff.core.soccer.league.service.dto.LeagueGameDTO;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.kickoff.core.soccer.game.QLeagueGame.leagueGame;


@Transactional(readOnly = true)
@Component
@RequiredArgsConstructor
public class LeagueGameQuerydslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public FindLeagueGamesResponse findLeagueTeamGame(Long leagueTeamId, Pageable pageable)
    {
        QLeagueGame leagueGame = QLeagueGame.leagueGame;
        List<LeagueGame> leagueTeamGames = jpaQueryFactory.selectFrom(leagueGame)
                .where(leagueGame.home.leagueTeamId.eq(leagueTeamId)
                        .or(leagueGame.away.leagueTeamId.eq(leagueTeamId)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return FindLeagueGamesResponse.of(
                new PageImpl<>(
                        leagueTeamGames,
                        pageable,
                        leagueTeamGames.size()
                )
        );
    }

    public FindLeagueGamesResponse findLeagueGames(FindGameCond condition)
    {
        QLeagueGame leagueGame = QLeagueGame.leagueGame;
        Pageable pageable = condition.pageable();

        List<LeagueGame> leagueGames = jpaQueryFactory.selectFrom(leagueGame)
                .where(gameDateEq(condition.startDate(), condition.endDate()),
                        leagueIdEq(condition.leagueId()), eqLeagueTeamId(condition.teamId())
                )
                .orderBy(leagueGame.gameDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = jpaQueryFactory.select(leagueGame.leagueGameId.count())
                .from(leagueGame)
                .where(gameDateEq(condition.startDate(), condition.endDate()),
                        leagueIdEq(condition.leagueId()))
                .fetchOne();

        return FindLeagueGamesResponse.of(
                new PageImpl<>(
                        leagueGames,
                        pageable,
                        count
                )
        );
    }

    private BooleanExpression gameDateEq(LocalDate startDate, LocalDate endDate)
    {
        if (startDate != null && endDate != null)
        {
            return leagueGame.gameDate.between(startDate.atStartOfDay(), endDate.atTime(23, 59, 59));
        } else if (startDate != null)
        {
            return leagueGame.gameDate.goe(startDate.atStartOfDay());
        } else if (endDate != null)
        {
            return leagueGame.gameDate.loe(endDate.atTime(23, 59, 59));
        } else
        {
            return null;
        }
    }


    private BooleanExpression gameDateEq(LocalDateTime startDate, LocalDateTime endDate)
    {
        if (startDate != null && endDate != null)
        {
            return leagueGame.gameDate.between(startDate, endDate);
        } else if (startDate != null)
        {
            return leagueGame.gameDate.goe(startDate);
        } else if (endDate != null)
        {
            return leagueGame.gameDate.loe(endDate);
        } else
        {
            return null;
        }
    }

    private BooleanExpression leagueIdEq(Long leagueId)
    {
        return leagueId != null ? leagueGame.away.league.leagueId.eq(leagueId) : null;
    }

    public Page<FindLeagueGameResponse> searchGame(GameSearchCondition condition, Pageable pageable)
    {
        QLeagueGame leagueGame = QLeagueGame.leagueGame;
        QueryResults<LeagueGame> results = jpaQueryFactory.selectFrom(leagueGame)
                .where(gameDateEq(condition.startDate(), condition.endDate()),
                        leagueIdEq(condition.leagueId())
                )
                .orderBy(leagueGame.gameDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<LeagueGame> content = results.getResults();
        long total = results.getTotal();
        List<FindLeagueGameResponse> responses = content.stream()
                .map(FindLeagueGameResponse::from)
                .collect(Collectors.toList());
        return new PageImpl<>(responses,pageable,total);
    }

    public List<LeagueGameDTO> findLeagueGamesWithDynamicParameter(Long leagueId, Long leagueTeamId, LocalDateTime startDateTime, LocalDateTime endDateTime)
    {
        List<LeagueGame> results = jpaQueryFactory.selectFrom(leagueGame)
                .where(
                        gameDateEq(startDateTime, endDateTime), leagueIdEq(leagueId), eqLeagueTeamId(leagueTeamId)
                )
                .orderBy(leagueGame.gameDate.desc())
                .fetch();

        return results.stream()
                .map(LeagueGameDTO::of)
                .collect(Collectors.toList());
    }

    private BooleanExpression eqLeagueTeamId(Long leagueTeamId)
    {
        return leagueTeamId != null ? (leagueGame.away.league.leagueId.eq(leagueTeamId)).or(leagueGame.home.league.leagueId.eq(leagueTeamId)) : null;
    }
}

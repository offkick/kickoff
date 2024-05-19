package com.kickoff.domain.soccer.player;

import com.kickoff.domain.soccer.player.dto.PlayerSearchCondition;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.kickoff.domain.soccer.player.QPlayer.player;

@Repository
@RequiredArgsConstructor
public class PlayerRepositoryImpl implements PlayerRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Player> searchPage(PlayerSearchCondition condition, Pageable pageable){
        QPlayer player = QPlayer.player;
        QueryResults<Player> results = jpaQueryFactory.selectFrom(player)
                .where(playerNameEq(condition.playerName()),
                        nationalEq(condition.national()),
                        leagueTeamEq(condition.leagueTeamId()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<Player> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content,pageable,total);

    }

    private BooleanExpression playerNameEq(String playerName) {
        return playerName != null && !playerName.isEmpty()? player.playerName.contains(playerName): null;
    }

    private BooleanExpression nationalEq(String national) {
        return national != null && !national.isEmpty() ? player.national.contains(national): null;
    }

    private BooleanExpression leagueTeamEq(Long LeagueTeamId) {
        return LeagueTeamId != null ? player.leagueTeam.leagueTeamId.eq(LeagueTeamId): null;
    }

}

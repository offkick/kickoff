package com.kickoff.core.soccer.player;

import com.kickoff.core.soccer.player.dto.PlayerDTO;
import com.kickoff.core.soccer.player.dto.PlayerSearchCondition;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.kickoff.core.soccer.player.QPlayer.player;

@Repository
@RequiredArgsConstructor
public class PlayerRepositoryImpl implements PlayerRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<PlayerDTO> searchPlayer(PlayerSearchCondition condition, Pageable pageable)
    {
        QPlayer player = QPlayer.player;
        QPlayerImage image = QPlayerImage.playerImage;
        QueryResults<Player> results = jpaQueryFactory.selectFrom(player)
                .leftJoin(player.playerImages,image).fetchJoin()
                .where(playerNameEq(condition.playerName()),
                        nationalEq(condition.national()),
                        leagueTeamEq(condition.leagueTeamId()))
                .orderBy(player.playerName.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<Player> content = results.getResults();
        long total = results.getTotal();
        List<PlayerDTO> responses = content.stream()
                .map(PlayerDTO::from)
                .collect(Collectors.toList());
        return new PageImpl<>(responses,pageable,total);

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

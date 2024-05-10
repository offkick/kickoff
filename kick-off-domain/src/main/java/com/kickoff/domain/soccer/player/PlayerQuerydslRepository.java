package com.kickoff.domain.soccer.player;

import com.kickoff.domain.team.league.QLeagueTeam;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

import static com.kickoff.domain.soccer.player.QPlayer.player;

@Component
@RequiredArgsConstructor
public class PlayerQuerydslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public Optional<Player> findPlayer(Long playerId)
    {
        QLeagueTeam leagueTeam = QLeagueTeam.leagueTeam;
        QPlayer player = QPlayer.player;

        Player findPlayer = jpaQueryFactory.selectFrom(player)
                .leftJoin(player.leagueTeam,leagueTeam).fetchJoin()
                .where(
                        player.playerId.eq(playerId)
                )
                .fetchOne();

        return Optional.ofNullable(findPlayer);
    }

    public List<Player> findAllByUsers(String playerName, String national, Long teamId) {
        QPlayer player = QPlayer.player;

        // QueryDSL을 사용하여 동적 쿼리 생성
        var query = jpaQueryFactory.selectFrom(player);

        // playerName 값이 있을 때만 조건 추가
        if (playerName != null && !playerName.isEmpty()) {
            query.where(player.playerName.contains(playerName));
        }

        // national 값이 있을 때만 조건 추가
        if (national != null && !national.isEmpty()) {
            query.where(player.national.contains(national));
        }

        // teamId 값이 있을 때만 조건 추가
        if (teamId != null) {
            query.where(player.leagueTeam.leagueTeamId.eq(teamId));
        }

        // 모든 값이 null인 경우 조건 추가하지 않고 모든 플레이어를 반환
        return query.fetch();
    }
//    private BooleanExpression playerNameEq(String playerName) {
//        if(!StringUtils.hasText(playerName)) return null;
//        return player.playerName.eq(playerName);
//    }
}

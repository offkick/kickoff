package com.kickoff.domain.soccer.player;

import com.kickoff.domain.soccer.team.league.QLeagueTeam;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Optional;

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

    public List<Player> findAllByUsers(String playerName, String national, Long LeagueTeamId) {
        QPlayer player = QPlayer.player;
        return jpaQueryFactory.selectFrom(player)
                .where(playerNameEq(playerName),
                        nationalEq(national),
                        leagueTeamEq(LeagueTeamId))
                .fetch();
    }
    private BooleanExpression playerNameEq(String playerName) {
        QPlayer player = QPlayer.player;
        return playerName != null && !playerName.isEmpty()? player.playerName.contains(playerName): null;
    }

    private BooleanExpression nationalEq(String national) {
        QPlayer player = QPlayer.player;
        return national != null && !national.isEmpty() ? player.national.contains(national): null;
    }

    private BooleanExpression leagueTeamEq(Long LeagueTeamId) {
        QPlayer player = QPlayer.player;
        return LeagueTeamId != null ? player.leagueTeam.leagueTeamId.eq(LeagueTeamId): null;
    }
}

package com.kickoff.domain.player;

import com.kickoff.domain.team.league.QLeagueTeam;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PlayerQuerydslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public Optional<Player> findPlayer(@PathVariable("playerId") Long playerId){
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
}

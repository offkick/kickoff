package com.kickoff.core.soccer.player;

import com.kickoff.core.soccer.player.dto.PlayerDTO;
import com.kickoff.core.soccer.team.league.QLeagueTeam;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    // public List<PlayerDTO> findAllByUsers(String playerName, String national, Long LeagueTeamId) {
    //     QPlayer player = QPlayer.player;
    //     List<Player> playerList = jpaQueryFactory.selectFrom(player)
    //             .where(playerNameEq(playerName),
    //                     nationalEq(national),
    //                     leagueTeamEq(LeagueTeamId))
    //             .fetch();
    //     List<PlayerDTO> playerDTO = playerList.stream().map(p -> new PlayerDTO(p.getPlayerId(),
    //             p.getNational(),
    //             p.getPlayerName(),
    //             p.getPosition(),
    //             p.getLeagueTeam().getLeagueTeamName(),
    //                     p.getLeagueTeam().getLeagueTeamId()))
    //             .collect(Collectors.toList());

    //     return playerDTO;
    // }
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

package com.kickoff.domain.team.league.game.player;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLeagueGamePlayer is a Querydsl query type for LeagueGamePlayer
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLeagueGamePlayer extends EntityPathBase<LeagueGamePlayer> {

    private static final long serialVersionUID = 926179545L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLeagueGamePlayer leagueGamePlayer = new QLeagueGamePlayer("leagueGamePlayer");

    public final NumberPath<Long> LeagueGamePlayerId = createNumber("LeagueGamePlayerId", Long.class);

    public final NumberPath<Integer> playedTime = createNumber("playedTime", Integer.class);

    public final com.kickoff.domain.player.QPlayer player;

    public final EnumPath<com.kickoff.domain.player.PlayerPosition> position = createEnum("position", com.kickoff.domain.player.PlayerPosition.class);

    public final EnumPath<LeagueGamePlayerStatus> status = createEnum("status", LeagueGamePlayerStatus.class);

    public final NumberPath<Integer> subTime = createNumber("subTime", Integer.class);

    public QLeagueGamePlayer(String variable) {
        this(LeagueGamePlayer.class, forVariable(variable), INITS);
    }

    public QLeagueGamePlayer(Path<? extends LeagueGamePlayer> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLeagueGamePlayer(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLeagueGamePlayer(PathMetadata metadata, PathInits inits) {
        this(LeagueGamePlayer.class, metadata, inits);
    }

    public QLeagueGamePlayer(Class<? extends LeagueGamePlayer> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.player = inits.isInitialized("player") ? new com.kickoff.domain.player.QPlayer(forProperty("player"), inits.get("player")) : null;
    }

}


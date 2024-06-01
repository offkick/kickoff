package com.kickoff.core.soccer.player;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPlayer is a Querydsl query type for Player
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPlayer extends EntityPathBase<Player> {

    private static final long serialVersionUID = -1172161434L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPlayer player = new QPlayer("player");

    public final StringPath birth = createString("birth");

    public final com.kickoff.core.soccer.team.league.QLeagueTeam leagueTeam;

    public final StringPath national = createString("national");

    public final NumberPath<Long> playerId = createNumber("playerId", Long.class);

    public final StringPath playerName = createString("playerName");

    public final EnumPath<PlayerPosition> position = createEnum("position", PlayerPosition.class);

    public final com.kickoff.core.soccer.team.league.QSeason season;

    public QPlayer(String variable) {
        this(Player.class, forVariable(variable), INITS);
    }

    public QPlayer(Path<? extends Player> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPlayer(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPlayer(PathMetadata metadata, PathInits inits) {
        this(Player.class, metadata, inits);
    }

    public QPlayer(Class<? extends Player> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.leagueTeam = inits.isInitialized("leagueTeam") ? new com.kickoff.core.soccer.team.league.QLeagueTeam(forProperty("leagueTeam"), inits.get("leagueTeam")) : null;
        this.season = inits.isInitialized("season") ? new com.kickoff.core.soccer.team.league.QSeason(forProperty("season")) : null;
    }

}


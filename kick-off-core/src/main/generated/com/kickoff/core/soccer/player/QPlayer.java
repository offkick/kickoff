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

    public final com.kickoff.core.QBaseEntity _super = new com.kickoff.core.QBaseEntity(this);

    public final StringPath birth = createString("birth");

    public final QContract contract;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final com.kickoff.core.soccer.league.QLeagueTeam leagueTeam;

    public final StringPath national = createString("national");

    public final NumberPath<Long> playerId = createNumber("playerId", Long.class);

    public final SetPath<PlayerImage, QPlayerImage> playerImages = this.<PlayerImage, QPlayerImage>createSet("playerImages", PlayerImage.class, QPlayerImage.class, PathInits.DIRECT2);

    public final StringPath playerName = createString("playerName");

    public final EnumPath<PlayerPosition> position = createEnum("position", PlayerPosition.class);

    public final com.kickoff.core.soccer.league.QSeason season;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

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
        this.contract = inits.isInitialized("contract") ? new QContract(forProperty("contract")) : null;
        this.leagueTeam = inits.isInitialized("leagueTeam") ? new com.kickoff.core.soccer.league.QLeagueTeam(forProperty("leagueTeam"), inits.get("leagueTeam")) : null;
        this.season = inits.isInitialized("season") ? new com.kickoff.core.soccer.league.QSeason(forProperty("season")) : null;
    }

}


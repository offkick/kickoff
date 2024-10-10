package com.kickoff.core.soccer.game;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGameStatistics is a Querydsl query type for GameStatistics
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGameStatistics extends EntityPathBase<GameStatistics> {

    private static final long serialVersionUID = -221685751L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGameStatistics gameStatistics = new QGameStatistics("gameStatistics");

    public final StringPath ballPossession = createString("ballPossession");

    public final StringPath cornerKicks = createString("cornerKicks");

    public final StringPath fouls = createString("fouls");

    public final StringPath freeKicks = createString("freeKicks");

    public final StringPath goalKicks = createString("goalKicks");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QLeagueGame leagueGame;

    public final StringPath offsides = createString("offsides");

    public final StringPath redCards = createString("redCards");

    public final StringPath saves = createString("saves");

    public final StringPath shots = createString("shots");

    public final StringPath shotsOffGoal = createString("shotsOffGoal");

    public final StringPath shotsOnGoal = createString("shotsOnGoal");

    public final StringPath throwIns = createString("throwIns");

    public final StringPath type = createString("type");

    public final StringPath yellowCards = createString("yellowCards");

    public final StringPath yellowRedCards = createString("yellowRedCards");

    public QGameStatistics(String variable) {
        this(GameStatistics.class, forVariable(variable), INITS);
    }

    public QGameStatistics(Path<? extends GameStatistics> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGameStatistics(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGameStatistics(PathMetadata metadata, PathInits inits) {
        this(GameStatistics.class, metadata, inits);
    }

    public QGameStatistics(Class<? extends GameStatistics> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.leagueGame = inits.isInitialized("leagueGame") ? new QLeagueGame(forProperty("leagueGame"), inits.get("leagueGame")) : null;
    }

}


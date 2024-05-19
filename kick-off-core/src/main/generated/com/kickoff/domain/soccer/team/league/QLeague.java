package com.kickoff.domain.soccer.team.league;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLeague is a Querydsl query type for League
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLeague extends EntityPathBase<League> {

    private static final long serialVersionUID = -58684726L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLeague league = new QLeague("league");

    public final NumberPath<Long> leagueId = createNumber("leagueId", Long.class);

    public final StringPath leagueName = createString("leagueName");

    public final EnumPath<com.kickoff.domain.common.National> national = createEnum("national", com.kickoff.domain.common.National.class);

    public final QSeason season;

    public final StringPath tier = createString("tier");

    public QLeague(String variable) {
        this(League.class, forVariable(variable), INITS);
    }

    public QLeague(Path<? extends League> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLeague(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLeague(PathMetadata metadata, PathInits inits) {
        this(League.class, metadata, inits);
    }

    public QLeague(Class<? extends League> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.season = inits.isInitialized("season") ? new QSeason(forProperty("season")) : null;
    }

}


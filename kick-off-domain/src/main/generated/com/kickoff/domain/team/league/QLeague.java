package com.kickoff.domain.team.league;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLeague is a Querydsl query type for League
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLeague extends EntityPathBase<League> {

    private static final long serialVersionUID = 454767893L;

    public static final QLeague league = new QLeague("league");

    public final NumberPath<Long> leagueId = createNumber("leagueId", Long.class);

    public final StringPath leagueName = createString("leagueName");

    public final EnumPath<com.kickoff.domain.common.National> national = createEnum("national", com.kickoff.domain.common.National.class);

    public final StringPath tier = createString("tier");

    public QLeague(String variable) {
        super(League.class, forVariable(variable));
    }

    public QLeague(Path<? extends League> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLeague(PathMetadata metadata) {
        super(League.class, metadata);
    }

}


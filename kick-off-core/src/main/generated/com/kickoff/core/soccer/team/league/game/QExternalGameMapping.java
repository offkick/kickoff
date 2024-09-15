package com.kickoff.core.soccer.team.league.game;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.kickoff.core.soccer.league.game.ExternalGameMapping;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QExternalGameMapping is a Querydsl query type for ExternalGameMapping
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExternalGameMapping extends EntityPathBase<ExternalGameMapping> {

    private static final long serialVersionUID = -1039513045L;

    public static final QExternalGameMapping externalGameMapping = new QExternalGameMapping("externalGameMapping");

    public final NumberPath<Long> externalGameId = createNumber("externalGameId", Long.class);

    public final NumberPath<Long> gameId = createNumber("gameId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QExternalGameMapping(String variable) {
        super(ExternalGameMapping.class, forVariable(variable));
    }

    public QExternalGameMapping(Path<? extends ExternalGameMapping> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExternalGameMapping(PathMetadata metadata) {
        super(ExternalGameMapping.class, metadata);
    }

}


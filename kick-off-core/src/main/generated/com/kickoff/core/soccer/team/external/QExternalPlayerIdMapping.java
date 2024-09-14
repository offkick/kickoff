package com.kickoff.core.soccer.team.external;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QExternalPlayerIdMapping is a Querydsl query type for ExternalPlayerIdMapping
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExternalPlayerIdMapping extends EntityPathBase<ExternalPlayerIdMapping> {

    private static final long serialVersionUID = 931701741L;

    public static final QExternalPlayerIdMapping externalPlayerIdMapping = new QExternalPlayerIdMapping("externalPlayerIdMapping");

    public final NumberPath<Long> externalPlayerId = createNumber("externalPlayerId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> playerId = createNumber("playerId", Long.class);

    public final StringPath season = createString("season");

    public QExternalPlayerIdMapping(String variable) {
        super(ExternalPlayerIdMapping.class, forVariable(variable));
    }

    public QExternalPlayerIdMapping(Path<? extends ExternalPlayerIdMapping> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExternalPlayerIdMapping(PathMetadata metadata) {
        super(ExternalPlayerIdMapping.class, metadata);
    }

}


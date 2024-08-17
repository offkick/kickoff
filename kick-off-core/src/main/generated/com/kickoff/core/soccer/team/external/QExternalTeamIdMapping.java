package com.kickoff.core.soccer.team.external;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QExternalTeamIdMapping is a Querydsl query type for ExternalTeamIdMapping
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExternalTeamIdMapping extends EntityPathBase<ExternalTeamIdMapping> {

    private static final long serialVersionUID = 146903729L;

    public static final QExternalTeamIdMapping externalTeamIdMapping = new QExternalTeamIdMapping("externalTeamIdMapping");

    public final EnumPath<ExternalApiName> externalApiName = createEnum("externalApiName", ExternalApiName.class);

    public final NumberPath<Long> externalId = createNumber("externalId", Long.class);

    public final NumberPath<Long> externalTeamId = createNumber("externalTeamId", Long.class);

    public final NumberPath<Long> teamId = createNumber("teamId", Long.class);

    public QExternalTeamIdMapping(String variable) {
        super(ExternalTeamIdMapping.class, forVariable(variable));
    }

    public QExternalTeamIdMapping(Path<? extends ExternalTeamIdMapping> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExternalTeamIdMapping(PathMetadata metadata) {
        super(ExternalTeamIdMapping.class, metadata);
    }

}


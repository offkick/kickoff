package com.kickoff.core.soccer.team.league;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSeason is a Querydsl query type for Season
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSeason extends EntityPathBase<Season> {

    private static final long serialVersionUID = -1784735975L;

    public static final QSeason season = new QSeason("season");

    public final com.kickoff.core.QBaseEntity _super = new com.kickoff.core.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final NumberPath<Long> seasonId = createNumber("seasonId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final StringPath years = createString("years");

    public QSeason(String variable) {
        super(Season.class, forVariable(variable));
    }

    public QSeason(Path<? extends Season> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSeason(PathMetadata metadata) {
        super(Season.class, metadata);
    }

}


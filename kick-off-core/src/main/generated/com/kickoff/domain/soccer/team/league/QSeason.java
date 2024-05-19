package com.kickoff.domain.soccer.team.league;

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

    private static final long serialVersionUID = 141730686L;

    public static final QSeason season1 = new QSeason("season1");

    public final StringPath season = createString("season");

    public final NumberPath<Long> seasonId = createNumber("seasonId", Long.class);

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


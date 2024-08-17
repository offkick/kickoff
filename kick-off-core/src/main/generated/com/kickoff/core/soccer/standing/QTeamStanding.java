package com.kickoff.core.soccer.standing;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTeamStanding is a Querydsl query type for TeamStanding
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTeamStanding extends EntityPathBase<TeamStanding> {

    private static final long serialVersionUID = -621210109L;

    public static final QTeamStanding teamStanding = new QTeamStanding("teamStanding");

    public final com.kickoff.core.QBaseEntity _super = new com.kickoff.core.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> draw = createNumber("draw", Integer.class);

    public final NumberPath<Integer> goalsAgainst = createNumber("goalsAgainst", Integer.class);

    public final NumberPath<Integer> goalsFor = createNumber("goalsFor", Integer.class);

    public final NumberPath<Integer> lost = createNumber("lost", Integer.class);

    public final NumberPath<Integer> points = createNumber("points", Integer.class);

    public final NumberPath<Integer> ranks = createNumber("ranks", Integer.class);

    public final NumberPath<Long> round = createNumber("round", Long.class);

    public final StringPath season = createString("season");

    public final NumberPath<Long> teamId = createNumber("teamId", Long.class);

    public final NumberPath<Long> teamStandingId = createNumber("teamStandingId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final NumberPath<Integer> won = createNumber("won", Integer.class);

    public QTeamStanding(String variable) {
        super(TeamStanding.class, forVariable(variable));
    }

    public QTeamStanding(Path<? extends TeamStanding> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTeamStanding(PathMetadata metadata) {
        super(TeamStanding.class, metadata);
    }

}


package com.kickoff.core.soccer.team.league;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.kickoff.core.soccer.league.Winner;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QWinner is a Querydsl query type for Winner
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWinner extends EntityPathBase<Winner> {

    private static final long serialVersionUID = -1666143115L;

    public static final QWinner winner = new QWinner("winner");

    public final com.kickoff.core.QBaseEntity _super = new com.kickoff.core.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> externalTeamId = createNumber("externalTeamId", Long.class);

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final NumberPath<Long> winnerId = createNumber("winnerId", Long.class);

    public final StringPath winnerName = createString("winnerName");

    public QWinner(String variable) {
        super(Winner.class, forVariable(variable));
    }

    public QWinner(Path<? extends Winner> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWinner(PathMetadata metadata) {
        super(Winner.class, metadata);
    }

}


package com.kickoff.domain.soccer.team;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QScore is a Querydsl query type for Score
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QScore extends EntityPathBase<Score> {

    private static final long serialVersionUID = 561899086L;

    public static final QScore score = new QScore("score");

    public final StringPath awayScore = createString("awayScore");

    public final StringPath homeScore = createString("homeScore");

    public final NumberPath<Long> scoreId = createNumber("scoreId", Long.class);

    public QScore(String variable) {
        super(Score.class, forVariable(variable));
    }

    public QScore(Path<? extends Score> path) {
        super(path.getType(), path.getMetadata());
    }

    public QScore(PathMetadata metadata) {
        super(Score.class, metadata);
    }

}


package com.kickoff.core.soccer.game;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QGameBooking is a Querydsl query type for GameBooking
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QGameBooking extends BeanPath<GameBooking> {

    private static final long serialVersionUID = 1022114611L;

    public static final QGameBooking gameBooking = new QGameBooking("gameBooking");

    public final StringPath cardType = createString("cardType");

    public final NumberPath<Long> leagueTeamId = createNumber("leagueTeamId", Long.class);

    public final NumberPath<Long> minute = createNumber("minute", Long.class);

    public final StringPath playerEnName = createString("playerEnName");

    public final NumberPath<Long> playerId = createNumber("playerId", Long.class);

    public final StringPath playerKrName = createString("playerKrName");

    public final StringPath type = createString("type");

    public QGameBooking(String variable) {
        super(GameBooking.class, forVariable(variable));
    }

    public QGameBooking(Path<? extends GameBooking> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGameBooking(PathMetadata metadata) {
        super(GameBooking.class, metadata);
    }

}


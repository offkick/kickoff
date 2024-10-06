package com.kickoff.core.soccer.game;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QGameLineUp is a Querydsl query type for GameLineUp
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QGameLineUp extends BeanPath<GameLineUp> {

    private static final long serialVersionUID = 590780309L;

    public static final QGameLineUp gameLineUp = new QGameLineUp("gameLineUp");

    public final StringPath playerEnName = createString("playerEnName");

    public final NumberPath<Long> playerId = createNumber("playerId", Long.class);

    public final StringPath playerKrName = createString("playerKrName");

    public final StringPath position = createString("position");

    public final StringPath shirtNumber = createString("shirtNumber");

    public final StringPath type = createString("type");

    public QGameLineUp(String variable) {
        super(GameLineUp.class, forVariable(variable));
    }

    public QGameLineUp(Path<? extends GameLineUp> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGameLineUp(PathMetadata metadata) {
        super(GameLineUp.class, metadata);
    }

}


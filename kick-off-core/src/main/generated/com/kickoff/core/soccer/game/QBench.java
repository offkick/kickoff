package com.kickoff.core.soccer.game;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBench is a Querydsl query type for Bench
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QBench extends BeanPath<Bench> {

    private static final long serialVersionUID = -554025380L;

    public static final QBench bench = new QBench("bench");

    public final StringPath playerEnName = createString("playerEnName");

    public final NumberPath<Long> playerId = createNumber("playerId", Long.class);

    public final StringPath playerKrName = createString("playerKrName");

    public final StringPath position = createString("position");

    public final StringPath shirtNumber = createString("shirtNumber");

    public final StringPath type = createString("type");

    public QBench(String variable) {
        super(Bench.class, forVariable(variable));
    }

    public QBench(Path<? extends Bench> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBench(PathMetadata metadata) {
        super(Bench.class, metadata);
    }

}


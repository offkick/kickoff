package com.kickoff.core.soccer.team;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSubstitutions is a Querydsl query type for Substitutions
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QSubstitutions extends BeanPath<Substitutions> {

    private static final long serialVersionUID = -1957430163L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSubstitutions substitutions = new QSubstitutions("substitutions");

    public final NumberPath<Integer> minute = createNumber("minute", Integer.class);

    public final com.kickoff.core.soccer.player.QPlayer playerI;

    public final com.kickoff.core.soccer.player.QPlayer playerOut;

    public QSubstitutions(String variable) {
        this(Substitutions.class, forVariable(variable), INITS);
    }

    public QSubstitutions(Path<? extends Substitutions> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSubstitutions(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSubstitutions(PathMetadata metadata, PathInits inits) {
        this(Substitutions.class, metadata, inits);
    }

    public QSubstitutions(Class<? extends Substitutions> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.playerI = inits.isInitialized("playerI") ? new com.kickoff.core.soccer.player.QPlayer(forProperty("playerI"), inits.get("playerI")) : null;
        this.playerOut = inits.isInitialized("playerOut") ? new com.kickoff.core.soccer.player.QPlayer(forProperty("playerOut"), inits.get("playerOut")) : null;
    }

}


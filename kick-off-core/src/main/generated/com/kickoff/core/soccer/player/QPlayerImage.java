package com.kickoff.core.soccer.player;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPlayerImage is a Querydsl query type for PlayerImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPlayerImage extends EntityPathBase<PlayerImage> {

    private static final long serialVersionUID = 1613319317L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPlayerImage playerImage = new QPlayerImage("playerImage");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> deletedAt = createDateTime("deletedAt", java.time.LocalDateTime.class);

    public final QPlayer player;

    public final NumberPath<Long> playerImageId = createNumber("playerImageId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final StringPath url = createString("url");

    public QPlayerImage(String variable) {
        this(PlayerImage.class, forVariable(variable), INITS);
    }

    public QPlayerImage(Path<? extends PlayerImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPlayerImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPlayerImage(PathMetadata metadata, PathInits inits) {
        this(PlayerImage.class, metadata, inits);
    }

    public QPlayerImage(Class<? extends PlayerImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.player = inits.isInitialized("player") ? new QPlayer(forProperty("player"), inits.get("player")) : null;
    }

}


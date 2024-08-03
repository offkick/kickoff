package com.kickoff.core.soccer.team.national;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNationalGame is a Querydsl query type for NationalGame
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNationalGame extends EntityPathBase<NationalGame> {

    private static final long serialVersionUID = 1998024727L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNationalGame nationalGame = new QNationalGame("nationalGame");

    public final com.kickoff.core.QBaseEntity _super = new com.kickoff.core.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final EnumPath<NationalGameType> nationalGameType = createEnum("nationalGameType", NationalGameType.class);

    public final NumberPath<Long> nationalScheduleId = createNumber("nationalScheduleId", Long.class);

    public final QNationalTeam nationalTeam;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QNationalGame(String variable) {
        this(NationalGame.class, forVariable(variable), INITS);
    }

    public QNationalGame(Path<? extends NationalGame> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QNationalGame(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QNationalGame(PathMetadata metadata, PathInits inits) {
        this(NationalGame.class, metadata, inits);
    }

    public QNationalGame(Class<? extends NationalGame> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.nationalTeam = inits.isInitialized("nationalTeam") ? new QNationalTeam(forProperty("nationalTeam")) : null;
    }

}


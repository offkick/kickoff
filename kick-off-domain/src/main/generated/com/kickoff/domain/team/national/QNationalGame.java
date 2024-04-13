package com.kickoff.domain.team.national;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.kickoff.domain.soccer.team.national.NationalGame;
import com.kickoff.domain.soccer.team.national.NationalGameType;
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

    private static final long serialVersionUID = 419165895L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNationalGame nationalGame = new QNationalGame("nationalGame");

    public final EnumPath<NationalGameType> nationalGameType = createEnum("nationalGameType", NationalGameType.class);

    public final NumberPath<Long> nationalScheduleId = createNumber("nationalScheduleId", Long.class);

    public final QNationalTeam nationalTeam;

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


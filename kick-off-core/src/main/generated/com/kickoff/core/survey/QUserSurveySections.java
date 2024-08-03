package com.kickoff.core.survey;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserSurveySections is a Querydsl query type for UserSurveySections
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserSurveySections extends EntityPathBase<UserSurveySections> {

    private static final long serialVersionUID = 1104201770L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserSurveySections userSurveySections = new QUserSurveySections("userSurveySections");

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public final QSurveySections surveySections;

    public final NumberPath<Long> userSurveySectionsId = createNumber("userSurveySectionsId", Long.class);

    public QUserSurveySections(String variable) {
        this(UserSurveySections.class, forVariable(variable), INITS);
    }

    public QUserSurveySections(Path<? extends UserSurveySections> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserSurveySections(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserSurveySections(PathMetadata metadata, PathInits inits) {
        this(UserSurveySections.class, metadata, inits);
    }

    public QUserSurveySections(Class<? extends UserSurveySections> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.surveySections = inits.isInitialized("surveySections") ? new QSurveySections(forProperty("surveySections"), inits.get("surveySections")) : null;
    }

}


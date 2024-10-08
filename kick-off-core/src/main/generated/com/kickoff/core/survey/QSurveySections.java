package com.kickoff.core.survey;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSurveySections is a Querydsl query type for SurveySections
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSurveySections extends EntityPathBase<SurveySections> {

    private static final long serialVersionUID = -1320381697L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSurveySections surveySections = new QSurveySections("surveySections");

    public final com.kickoff.core.QBaseEntity _super = new com.kickoff.core.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final StringPath sectionName = createString("sectionName");

    public final BooleanPath sectionRequiredYn = createBoolean("sectionRequiredYn");

    public final StringPath sectionTitle = createString("sectionTitle");

    public final QSurveyHeaders surveyHeaders;

    public final NumberPath<Long> surveySectionId = createNumber("surveySectionId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QSurveySections(String variable) {
        this(SurveySections.class, forVariable(variable), INITS);
    }

    public QSurveySections(Path<? extends SurveySections> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSurveySections(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSurveySections(PathMetadata metadata, PathInits inits) {
        this(SurveySections.class, metadata, inits);
    }

    public QSurveySections(Class<? extends SurveySections> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.surveyHeaders = inits.isInitialized("surveyHeaders") ? new QSurveyHeaders(forProperty("surveyHeaders")) : null;
    }

}


package com.kickoff.core.survey;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSurveyInputType is a Querydsl query type for SurveyInputType
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSurveyInputType extends EntityPathBase<SurveyInputType> {

    private static final long serialVersionUID = 108579699L;

    public static final QSurveyInputType surveyInputType = new QSurveyInputType("surveyInputType");

    public final com.kickoff.core.QBaseEntity _super = new com.kickoff.core.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath inputTypeName = createString("inputTypeName");

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final NumberPath<Long> surveyInputTypeId = createNumber("surveyInputTypeId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QSurveyInputType(String variable) {
        super(SurveyInputType.class, forVariable(variable));
    }

    public QSurveyInputType(Path<? extends SurveyInputType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSurveyInputType(PathMetadata metadata) {
        super(SurveyInputType.class, metadata);
    }

}


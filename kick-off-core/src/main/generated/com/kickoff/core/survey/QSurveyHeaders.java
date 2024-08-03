package com.kickoff.core.survey;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSurveyHeaders is a Querydsl query type for SurveyHeaders
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSurveyHeaders extends EntityPathBase<SurveyHeaders> {

    private static final long serialVersionUID = 583589013L;

    public static final QSurveyHeaders surveyHeaders = new QSurveyHeaders("surveyHeaders");

    public final StringPath instruction = createString("instruction");

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final NumberPath<Long> surveyHeaderId = createNumber("surveyHeaderId", Long.class);

    public final StringPath surveyName = createString("surveyName");

    public QSurveyHeaders(String variable) {
        super(SurveyHeaders.class, forVariable(variable));
    }

    public QSurveyHeaders(Path<? extends SurveyHeaders> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSurveyHeaders(PathMetadata metadata) {
        super(SurveyHeaders.class, metadata);
    }

}


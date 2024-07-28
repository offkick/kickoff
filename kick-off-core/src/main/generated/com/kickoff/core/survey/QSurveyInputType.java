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

    public final StringPath inputTypeName = createString("inputTypeName");

    public final NumberPath<Long> surveyInputTypeId = createNumber("surveyInputTypeId", Long.class);

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


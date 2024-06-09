package com.kickoff.core.survey;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQuestions is a Querydsl query type for Questions
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQuestions extends EntityPathBase<Questions> {

    private static final long serialVersionUID = 1677599638L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuestions questions = new QQuestions("questions");

    public final BooleanPath answerRequiredYn = createBoolean("answerRequiredYn");

    public final BooleanPath multipleOptionAnswers = createBoolean("multipleOptionAnswers");

    public final NumberPath<Long> optionGroupsId = createNumber("optionGroupsId", Long.class);

    public final NumberPath<Long> questionId = createNumber("questionId", Long.class);

    public final StringPath questionName = createString("questionName");

    public final StringPath questionSubtext = createString("questionSubtext");

    public final NumberPath<Long> surveyInputTypeId = createNumber("surveyInputTypeId", Long.class);

    public final QSurveySections surveySection;

    public QQuestions(String variable) {
        this(Questions.class, forVariable(variable), INITS);
    }

    public QQuestions(Path<? extends Questions> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQuestions(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQuestions(PathMetadata metadata, PathInits inits) {
        this(Questions.class, metadata, inits);
    }

    public QQuestions(Class<? extends Questions> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.surveySection = inits.isInitialized("surveySection") ? new QSurveySections(forProperty("surveySection"), inits.get("surveySection")) : null;
    }

}


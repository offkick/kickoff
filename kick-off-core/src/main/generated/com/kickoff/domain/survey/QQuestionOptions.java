package com.kickoff.domain.survey;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQuestionOptions is a Querydsl query type for QuestionOptions
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQuestionOptions extends EntityPathBase<QuestionOptions> {

    private static final long serialVersionUID = 1976987932L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuestionOptions questionOptions = new QQuestionOptions("questionOptions");

    public final QOptionChoices optionChoices;

    public final NumberPath<Long> questionOptionId = createNumber("questionOptionId", Long.class);

    public final QQuestions questions;

    public QQuestionOptions(String variable) {
        this(QuestionOptions.class, forVariable(variable), INITS);
    }

    public QQuestionOptions(Path<? extends QuestionOptions> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQuestionOptions(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQuestionOptions(PathMetadata metadata, PathInits inits) {
        this(QuestionOptions.class, metadata, inits);
    }

    public QQuestionOptions(Class<? extends QuestionOptions> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.optionChoices = inits.isInitialized("optionChoices") ? new QOptionChoices(forProperty("optionChoices"), inits.get("optionChoices")) : null;
        this.questions = inits.isInitialized("questions") ? new QQuestions(forProperty("questions"), inits.get("questions")) : null;
    }

}


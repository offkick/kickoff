package com.kickoff.core.survey;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOptionChoices is a Querydsl query type for OptionChoices
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOptionChoices extends EntityPathBase<OptionChoices> {

    private static final long serialVersionUID = 1438219910L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOptionChoices optionChoices = new QOptionChoices("optionChoices");

    public final com.kickoff.core.QBaseEntity _super = new com.kickoff.core.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final StringPath optionChoiceName = createString("optionChoiceName");

    public final NumberPath<Long> optionChoicesId = createNumber("optionChoicesId", Long.class);

    public final QOptionGroups optionGroups;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QOptionChoices(String variable) {
        this(OptionChoices.class, forVariable(variable), INITS);
    }

    public QOptionChoices(Path<? extends OptionChoices> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOptionChoices(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOptionChoices(PathMetadata metadata, PathInits inits) {
        this(OptionChoices.class, metadata, inits);
    }

    public QOptionChoices(Class<? extends OptionChoices> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.optionGroups = inits.isInitialized("optionGroups") ? new QOptionGroups(forProperty("optionGroups")) : null;
    }

}


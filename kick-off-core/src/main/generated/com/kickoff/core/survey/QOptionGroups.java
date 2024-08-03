package com.kickoff.core.survey;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOptionGroups is a Querydsl query type for OptionGroups
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOptionGroups extends EntityPathBase<OptionGroups> {

    private static final long serialVersionUID = -1908052032L;

    public static final QOptionGroups optionGroups = new QOptionGroups("optionGroups");

    public final com.kickoff.core.QBaseEntity _super = new com.kickoff.core.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final ListPath<OptionChoices, QOptionChoices> optionChoicesList = this.<OptionChoices, QOptionChoices>createList("optionChoicesList", OptionChoices.class, QOptionChoices.class, PathInits.DIRECT2);

    public final NumberPath<Long> optionGroupId = createNumber("optionGroupId", Long.class);

    public final StringPath optionGroupName = createString("optionGroupName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QOptionGroups(String variable) {
        super(OptionGroups.class, forVariable(variable));
    }

    public QOptionGroups(Path<? extends OptionGroups> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOptionGroups(PathMetadata metadata) {
        super(OptionGroups.class, metadata);
    }

}


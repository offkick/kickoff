package com.kickoff.core.soccer.player;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPlayerComment is a Querydsl query type for PlayerComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPlayerComment extends EntityPathBase<PlayerComment> {

    private static final long serialVersionUID = -1044862183L;

    public static final QPlayerComment playerComment = new QPlayerComment("playerComment");

    public final com.kickoff.core.QBaseEntity _super = new com.kickoff.core.QBaseEntity(this);

    public final StringPath comment = createString("comment");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final BooleanPath deleted = createBoolean("deleted");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public final NumberPath<Long> playerId = createNumber("playerId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QPlayerComment(String variable) {
        super(PlayerComment.class, forVariable(variable));
    }

    public QPlayerComment(Path<? extends PlayerComment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPlayerComment(PathMetadata metadata) {
        super(PlayerComment.class, metadata);
    }

}


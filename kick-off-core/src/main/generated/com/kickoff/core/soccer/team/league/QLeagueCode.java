package com.kickoff.core.soccer.team.league;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.kickoff.core.soccer.league.LeagueCode;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLeagueCode is a Querydsl query type for LeagueCode
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLeagueCode extends EntityPathBase<LeagueCode> {

    private static final long serialVersionUID = -726833678L;

    public static final QLeagueCode leagueCode1 = new QLeagueCode("leagueCode1");

    public final com.kickoff.core.QBaseEntity _super = new com.kickoff.core.QBaseEntity(this);

    public final StringPath caption = createString("caption");

    public final StringPath country = createString("country");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath leagueCode = createString("leagueCode");

    public final StringPath leagueCodeName = createString("leagueCodeName");

    public final NumberPath<Long> leagueId = createNumber("leagueId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QLeagueCode(String variable) {
        super(LeagueCode.class, forVariable(variable));
    }

    public QLeagueCode(Path<? extends LeagueCode> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLeagueCode(PathMetadata metadata) {
        super(LeagueCode.class, metadata);
    }

}


package com.kickoff.core.soccer.team.national;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QNationalTeam is a Querydsl query type for NationalTeam
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNationalTeam extends EntityPathBase<NationalTeam> {

    private static final long serialVersionUID = 1998415490L;

    public static final QNationalTeam nationalTeam = new QNationalTeam("nationalTeam");

    public final NumberPath<Long> nationalTeamId = createNumber("nationalTeamId", Long.class);

    public final StringPath nationalTeamName = createString("nationalTeamName");

    public final EnumPath<com.kickoff.core.soccer.team.TeamType> teamType = createEnum("teamType", com.kickoff.core.soccer.team.TeamType.class);

    public QNationalTeam(String variable) {
        super(NationalTeam.class, forVariable(variable));
    }

    public QNationalTeam(Path<? extends NationalTeam> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNationalTeam(PathMetadata metadata) {
        super(NationalTeam.class, metadata);
    }

}


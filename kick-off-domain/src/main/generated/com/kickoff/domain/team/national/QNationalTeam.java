package com.kickoff.domain.team.national;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.kickoff.domain.soccer.team.TeamType;
import com.kickoff.domain.soccer.team.national.NationalTeam;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QNationalTeam is a Querydsl query type for NationalTeam
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNationalTeam extends EntityPathBase<NationalTeam> {

    private static final long serialVersionUID = 419556658L;

    public static final QNationalTeam nationalTeam = new QNationalTeam("nationalTeam");

    public final NumberPath<Long> nationalTeamId = createNumber("nationalTeamId", Long.class);

    public final StringPath nationalTeamName = createString("nationalTeamName");

    public final EnumPath<TeamType> teamType = createEnum("teamType", TeamType.class);

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


package com.kickoff.core.soccer.team.league;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLeagueTeam is a Querydsl query type for LeagueTeam
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLeagueTeam extends EntityPathBase<LeagueTeam> {

    private static final long serialVersionUID = -726336926L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLeagueTeam leagueTeam = new QLeagueTeam("leagueTeam");

    public final QLeague league;

    public final NumberPath<Long> leagueTeamId = createNumber("leagueTeamId", Long.class);

    public final StringPath leagueTeamName = createString("leagueTeamName");

    public final StringPath logo = createString("logo");

    public final QSeason season;

    public final EnumPath<com.kickoff.core.soccer.team.TeamType> teamType = createEnum("teamType", com.kickoff.core.soccer.team.TeamType.class);

    public QLeagueTeam(String variable) {
        this(LeagueTeam.class, forVariable(variable), INITS);
    }

    public QLeagueTeam(Path<? extends LeagueTeam> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLeagueTeam(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLeagueTeam(PathMetadata metadata, PathInits inits) {
        this(LeagueTeam.class, metadata, inits);
    }

    public QLeagueTeam(Class<? extends LeagueTeam> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.league = inits.isInitialized("league") ? new QLeague(forProperty("league"), inits.get("league")) : null;
        this.season = inits.isInitialized("season") ? new QSeason(forProperty("season")) : null;
    }

}


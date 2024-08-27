package com.kickoff.core.soccer.team.league;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLeagueGameStatic is a Querydsl query type for LeagueGameStatic
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLeagueGameStatic extends EntityPathBase<LeagueGameStatic> {

    private static final long serialVersionUID = -1502172859L;

    public static final QLeagueGameStatic leagueGameStatic = new QLeagueGameStatic("leagueGameStatic");

    public final NumberPath<Long> awayLeagueTeamId = createNumber("awayLeagueTeamId", Long.class);

    public final StringPath awayScore = createString("awayScore");

    public final NumberPath<Integer> count = createNumber("matchDay", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> gameDate = createDateTime("gameDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> homeLeagueTeamId = createNumber("homeLeagueTeamId", Long.class);

    public final StringPath homeScore = createString("homeScore");

    public final NumberPath<Long> leagueGameStaticId = createNumber("leagueGameStaticId", Long.class);

    public final EnumPath<com.kickoff.core.soccer.team.league.game.LeagueGameStatus> leagueGameStatus = createEnum("leagueGameStatus", com.kickoff.core.soccer.team.league.game.LeagueGameStatus.class);

    public final NumberPath<Long> seasonId = createNumber("seasonId", Long.class);

    public QLeagueGameStatic(String variable) {
        super(LeagueGameStatic.class, forVariable(variable));
    }

    public QLeagueGameStatic(Path<? extends LeagueGameStatic> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLeagueGameStatic(PathMetadata metadata) {
        super(LeagueGameStatic.class, metadata);
    }

}


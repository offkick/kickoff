package com.kickoff.core.soccer.player;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLeaguePlayerRank is a Querydsl query type for LeaguePlayerRank
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLeaguePlayerRank extends EntityPathBase<LeaguePlayerRank> {

    private static final long serialVersionUID = 56803713L;

    public static final QLeaguePlayerRank leaguePlayerRank = new QLeaguePlayerRank("leaguePlayerRank");

    public final NumberPath<Long> assists = createNumber("assists", Long.class);

    public final NumberPath<Long> goals = createNumber("goals", Long.class);

    public final NumberPath<Long> leaguePlayerRankId = createNumber("leaguePlayerRankId", Long.class);

    public final NumberPath<Long> leagueTeamId = createNumber("leagueTeamId", Long.class);

    public final NumberPath<Long> matchDay = createNumber("matchDay", Long.class);

    public final NumberPath<Long> penaltie = createNumber("penaltie", Long.class);

    public final NumberPath<Long> playerId = createNumber("playerId", Long.class);

    public final StringPath position = createString("position");

    public final StringPath section = createString("section");

    public QLeaguePlayerRank(String variable) {
        super(LeaguePlayerRank.class, forVariable(variable));
    }

    public QLeaguePlayerRank(Path<? extends LeaguePlayerRank> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLeaguePlayerRank(PathMetadata metadata) {
        super(LeaguePlayerRank.class, metadata);
    }

}


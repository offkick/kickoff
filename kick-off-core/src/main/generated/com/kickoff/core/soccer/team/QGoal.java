package com.kickoff.core.soccer.team;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGoal is a Querydsl query type for Goal
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGoal extends EntityPathBase<Goal> {

    private static final long serialVersionUID = 745075164L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGoal goal = new QGoal("goal");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> minute = createNumber("minute", Integer.class);

    public final com.kickoff.core.soccer.player.QPlayer player;

    public final com.kickoff.core.soccer.team.league.QLeagueTeam scoredTeam;

    public final EnumPath<GoalType> type = createEnum("type", GoalType.class);

    public QGoal(String variable) {
        this(Goal.class, forVariable(variable), INITS);
    }

    public QGoal(Path<? extends Goal> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGoal(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGoal(PathMetadata metadata, PathInits inits) {
        this(Goal.class, metadata, inits);
    }

    public QGoal(Class<? extends Goal> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.player = inits.isInitialized("player") ? new com.kickoff.core.soccer.player.QPlayer(forProperty("player"), inits.get("player")) : null;
        this.scoredTeam = inits.isInitialized("scoredTeam") ? new com.kickoff.core.soccer.team.league.QLeagueTeam(forProperty("scoredTeam"), inits.get("scoredTeam")) : null;
    }

}


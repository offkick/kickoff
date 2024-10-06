package com.kickoff.core.soccer.game;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSubstitutions is a Querydsl query type for Substitutions
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSubstitutions extends EntityPathBase<Substitutions> {

    private static final long serialVersionUID = 289348930L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSubstitutions substitutions = new QSubstitutions("substitutions");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QLeagueGame leagueGame;

    public final NumberPath<Integer> minute = createNumber("minute", Integer.class);

    public final com.kickoff.core.soccer.player.QPlayer playerIn;

    public final com.kickoff.core.soccer.player.QPlayer playerOut;

    public final com.kickoff.core.soccer.league.QLeagueTeam substitutionTeam;

    public QSubstitutions(String variable) {
        this(Substitutions.class, forVariable(variable), INITS);
    }

    public QSubstitutions(Path<? extends Substitutions> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSubstitutions(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSubstitutions(PathMetadata metadata, PathInits inits) {
        this(Substitutions.class, metadata, inits);
    }

    public QSubstitutions(Class<? extends Substitutions> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.leagueGame = inits.isInitialized("leagueGame") ? new QLeagueGame(forProperty("leagueGame"), inits.get("leagueGame")) : null;
        this.playerIn = inits.isInitialized("playerIn") ? new com.kickoff.core.soccer.player.QPlayer(forProperty("playerIn"), inits.get("playerIn")) : null;
        this.playerOut = inits.isInitialized("playerOut") ? new com.kickoff.core.soccer.player.QPlayer(forProperty("playerOut"), inits.get("playerOut")) : null;
        this.substitutionTeam = inits.isInitialized("substitutionTeam") ? new com.kickoff.core.soccer.league.QLeagueTeam(forProperty("substitutionTeam"), inits.get("substitutionTeam")) : null;
    }

}


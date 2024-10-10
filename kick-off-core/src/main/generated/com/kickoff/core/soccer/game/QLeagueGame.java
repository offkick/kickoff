package com.kickoff.core.soccer.game;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLeagueGame is a Querydsl query type for LeagueGame
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLeagueGame extends EntityPathBase<LeagueGame> {

    private static final long serialVersionUID = 178418197L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLeagueGame leagueGame = new QLeagueGame("leagueGame");

    public final com.kickoff.core.QBaseEntity _super = new com.kickoff.core.QBaseEntity(this);

    public final com.kickoff.core.soccer.league.QLeagueTeam away;

    public final StringPath awayFormation = createString("awayFormation");

    public final ListPath<com.kickoff.core.soccer.game.player.LeagueGamePlayer, com.kickoff.core.soccer.game.player.QLeagueGamePlayer> awayPlayers = this.<com.kickoff.core.soccer.game.player.LeagueGamePlayer, com.kickoff.core.soccer.game.player.QLeagueGamePlayer>createList("awayPlayers", com.kickoff.core.soccer.game.player.LeagueGamePlayer.class, com.kickoff.core.soccer.game.player.QLeagueGamePlayer.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final ListPath<GameBooking, QGameBooking> gameBookings = this.<GameBooking, QGameBooking>createList("gameBookings", GameBooking.class, QGameBooking.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> gameDate = createDateTime("gameDate", java.time.LocalDateTime.class);

    public final ListPath<GameLineUp, QGameLineUp> gameLineUps = this.<GameLineUp, QGameLineUp>createList("gameLineUps", GameLineUp.class, QGameLineUp.class, PathInits.DIRECT2);

    public final ListPath<com.kickoff.core.soccer.team.Goal, com.kickoff.core.soccer.team.QGoal> goals = this.<com.kickoff.core.soccer.team.Goal, com.kickoff.core.soccer.team.QGoal>createList("goals", com.kickoff.core.soccer.team.Goal.class, com.kickoff.core.soccer.team.QGoal.class, PathInits.DIRECT2);

    public final com.kickoff.core.soccer.league.QLeagueTeam home;

    public final StringPath homeFormation = createString("homeFormation");

    public final ListPath<com.kickoff.core.soccer.game.player.LeagueGamePlayer, com.kickoff.core.soccer.game.player.QLeagueGamePlayer> homePlayers = this.<com.kickoff.core.soccer.game.player.LeagueGamePlayer, com.kickoff.core.soccer.game.player.QLeagueGamePlayer>createList("homePlayers", com.kickoff.core.soccer.game.player.LeagueGamePlayer.class, com.kickoff.core.soccer.game.player.QLeagueGamePlayer.class, PathInits.DIRECT2);

    public final NumberPath<Long> injuryTime = createNumber("injuryTime", Long.class);

    public final NumberPath<Long> leagueGameId = createNumber("leagueGameId", Long.class);

    public final EnumPath<LeagueGameStatus> leagueGameStatus = createEnum("leagueGameStatus", LeagueGameStatus.class);

    public final NumberPath<Integer> matchDay = createNumber("matchDay", Integer.class);

    public final NumberPath<Long> minute = createNumber("minute", Long.class);

    public final com.kickoff.core.soccer.team.QScore score;

    public final com.kickoff.core.soccer.league.QSeason season;

    public final ListPath<GameStatistics, QGameStatistics> statistics = this.<GameStatistics, QGameStatistics>createList("statistics", GameStatistics.class, QGameStatistics.class, PathInits.DIRECT2);

    public final ListPath<Substitutions, QSubstitutions> substitutionsList = this.<Substitutions, QSubstitutions>createList("substitutionsList", Substitutions.class, QSubstitutions.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final StringPath venue = createString("venue");

    public QLeagueGame(String variable) {
        this(LeagueGame.class, forVariable(variable), INITS);
    }

    public QLeagueGame(Path<? extends LeagueGame> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLeagueGame(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLeagueGame(PathMetadata metadata, PathInits inits) {
        this(LeagueGame.class, metadata, inits);
    }

    public QLeagueGame(Class<? extends LeagueGame> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.away = inits.isInitialized("away") ? new com.kickoff.core.soccer.league.QLeagueTeam(forProperty("away"), inits.get("away")) : null;
        this.home = inits.isInitialized("home") ? new com.kickoff.core.soccer.league.QLeagueTeam(forProperty("home"), inits.get("home")) : null;
        this.score = inits.isInitialized("score") ? new com.kickoff.core.soccer.team.QScore(forProperty("score")) : null;
        this.season = inits.isInitialized("season") ? new com.kickoff.core.soccer.league.QSeason(forProperty("season")) : null;
    }

}


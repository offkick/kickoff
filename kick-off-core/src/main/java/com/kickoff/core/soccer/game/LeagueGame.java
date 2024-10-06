package com.kickoff.core.soccer.game;

import com.kickoff.core.BaseEntity;
import com.kickoff.core.soccer.league.LeagueTeam;
import com.kickoff.core.soccer.player.PlayerPosition;
import com.kickoff.core.soccer.team.Goal;
import com.kickoff.core.soccer.team.Score;
import com.kickoff.core.soccer.league.Season;
import com.kickoff.core.soccer.game.player.LeagueGamePlayer;
import com.kickoff.core.soccer.game.player.LeagueGamePlayerStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.kickoff.core.soccer.player.PlayerPosition.KEEPER;
import static com.kickoff.core.soccer.player.PlayerPosition.values;


@Getter
@NoArgsConstructor
@Entity
@ToString(exclude = {"homePlayers", "awayPlayers"})
public class LeagueGame extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leagueGameId;

    private LocalDateTime gameDate;

    private int matchDay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "away_team_id")
    private LeagueTeam away;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_team_id")
    private LeagueTeam home;

    @Setter
    private Long injuryTime;

    @Setter
    private Long minute;
    @Embedded
    @Setter
    private Score score;

    @Enumerated(EnumType.STRING)
    private LeagueGameStatus leagueGameStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id")
    private Season season;

    private String venue;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<LeagueGamePlayer> homePlayers = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<LeagueGamePlayer> awayPlayers = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Setter
    private List<Goal> goals = new ArrayList<>();

    @Setter
    @ElementCollection
    @CollectionTable(name = "game_line_up",
            joinColumns = @JoinColumn(name = "leagueGameId"))
    private List<GameLineUp> gameLineUps = new ArrayList<>();

    @Setter

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "leagueGame")
    private List<Substitutions> substitutionsList = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "game_booking",
            joinColumns = @JoinColumn(name = "leagueGameId"))
    private List<GameBooking> gameBookings = new ArrayList<>();

    private String homeFormation;
    private String awayFormation;

    public void settingFormation(String home, String away)
    {
        this.awayFormation = away;
        this.homeFormation = home;
    }

    @Builder
    public LeagueGame(
            Long leagueGameId,
            LocalDateTime gameDate,
            int matchDay,
            LeagueTeam away,
            LeagueTeam home,
            Score score,
            LeagueGameStatus leagueGameStatus,
            Season season,
            List<LeagueGamePlayer> homePlayers,
            List<LeagueGamePlayer> awayPlayers,
            List<Goal> goals,
            String venue,
            String homeFormation, String awayFormation) {
        this.leagueGameId = leagueGameId;
        this.gameDate = gameDate;
        this.matchDay = matchDay;
        this.away = away;
        this.home = home;
        this.score = score;
        this.leagueGameStatus = leagueGameStatus;
        this.season = season;
        this.venue = venue;
        this.homePlayers = homePlayers;
        this.awayPlayers = awayPlayers;
        this.goals = goals;
        this.homeFormation = homeFormation;
        this.awayFormation = awayFormation;
    }

    private void validate(LeagueTeam leagueTeam, List<LeagueGamePlayer> leagueGamePlayers)
    {
        Set<PlayerPosition> playerPositions = Arrays.stream(values())
                .collect(Collectors.toSet());

        Set<LeagueGamePlayer> startingPlayers = leagueGamePlayers.stream()
                .filter(leagueGamePlayer -> leagueGamePlayer.getStatus().equals(LeagueGamePlayerStatus.STARTING))
                .collect(Collectors.toSet());

        if ((long) startingPlayers.size() != 11)
        {
            throw new IllegalArgumentException("출전 선수 11명 안됨");
        }

        Set<PlayerPosition> leagueGamePlayerPositionSet = startingPlayers.stream()
                .map(LeagueGamePlayer::getPosition)
                .collect(Collectors.toSet());

        if (!leagueGamePlayerPositionSet.containsAll(playerPositions))
        {
            throw new IllegalArgumentException("모든 포지션 없음");
        }

        if(startingPlayers.stream()
                .filter(leagueGamePlayer -> KEEPER.equals(leagueGamePlayer.getPosition()))
                .count() != 1
        ) {
            throw new IllegalArgumentException("키퍼가 1명 아님");
        }

        if (leagueGamePlayers.stream()
                .anyMatch(s-> !s.getPlayer().getLeagueTeam().equals(leagueTeam))
        ) {
            throw new IllegalArgumentException("팀이 다름");
        }
    }

    public void updateGameStatue(String status)
    {
        this.leagueGameStatus = updateStatus(status);
    }

    private LeagueGameStatus updateStatus(String status)
    {
        return switch (status) {
            case "IN_PLAY", "GAMING" -> LeagueGameStatus.GAMING;
            case "FINISHED" -> LeagueGameStatus.END;
            case "CANCELED" -> LeagueGameStatus.CANCELED;
            default -> LeagueGameStatus.BEFORE;
        };
    }

    public void endGame()
    {
        this.leagueGameStatus = LeagueGameStatus.END;
    }

    public void addGameLineUp(GameLineUp gameLineUp)
    {
        this.getGameLineUps().add(gameLineUp);
    }

    public void addSubstitutions(Substitutions substitutions)
    {
        this.getSubstitutionsList().add(substitutions);


    public void addGameBookings(GameBooking gameBooking)
    {
        this.getGameBookings().add(gameBooking);

    }
}

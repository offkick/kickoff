package com.kickoff.core.soccer.league.game;

import com.kickoff.core.BaseEntity;
import com.kickoff.core.soccer.league.LeagueTeam;
import com.kickoff.core.soccer.player.PlayerPosition;
import com.kickoff.core.soccer.team.Goal;
import com.kickoff.core.soccer.team.Score;
import com.kickoff.core.soccer.league.Season;
import com.kickoff.core.soccer.league.game.player.LeagueGamePlayer;
import com.kickoff.core.soccer.league.game.player.LeagueGamePlayerStatus;
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
            String venue
    ) {
        this.leagueGameId = leagueGameId;
        this.gameDate = gameDate;
        this.matchDay = matchDay;
        this.away = away;
        this.home = home;
        this.score = score;
        this.leagueGameStatus = leagueGameStatus;
        this.season = season;
        this.venue = venue;
//        validate(home, homePlayers);
//        validate(away, awayPlayers);
        this.homePlayers = homePlayers;
        this.awayPlayers = awayPlayers;
        this.goals = goals;
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
        switch (status){
            case "GAMING":
                return LeagueGameStatus.GAMING;
            case "FINISHED":
                return LeagueGameStatus.END;
            case "CANCELED":
                return LeagueGameStatus.CANCELED;
            default:
                return LeagueGameStatus.BEFORE;
        }
    }

    public void endGame()
    {
        this.leagueGameStatus = LeagueGameStatus.END;
    }
}

package com.kickoff.domain.soccer.team.league.game;

import com.kickoff.domain.soccer.player.PlayerPosition;
import com.kickoff.domain.soccer.team.Score;
import com.kickoff.domain.soccer.team.league.Season;
import com.kickoff.domain.soccer.team.league.game.player.LeagueGamePlayer;
import com.kickoff.domain.soccer.team.league.game.player.LeagueGamePlayerStatus;
import com.kickoff.domain.team.league.LeagueTeam;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.kickoff.domain.soccer.player.PlayerPosition.*;

@Getter
@NoArgsConstructor
@Entity
public class LeagueGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leagueGameId;

    private LocalDateTime gameDate;

    private int count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "away_team_id")
    private LeagueTeam away;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_team_id")
    private LeagueTeam home;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "score_id")
    private Score score;

    @Enumerated(EnumType.STRING)
    private LeagueGameStatus leagueGameStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id")
    private Season season;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<LeagueGamePlayer> homePlayers = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<LeagueGamePlayer> awayPlayers = new ArrayList<>();

    @Builder
    public LeagueGame(
            Long leagueGameId,
            LocalDateTime gameDate,
            int count, LeagueTeam away,
            LeagueTeam home,
            Score score,
            LeagueGameStatus leagueGameStatus,
            Season season,
            List<LeagueGamePlayer> homePlayers,
            List<LeagueGamePlayer> awayPlayers
    ) {
        this.leagueGameId = leagueGameId;
        this.gameDate = gameDate;
        this.count = count;
        this.away = away;
        this.home = home;
        this.score = score;
        this.leagueGameStatus = leagueGameStatus;
        this.season = season;
        validate(home, homePlayers);
        validate(away, awayPlayers);
        this.homePlayers = homePlayers;
        this.awayPlayers = awayPlayers;
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
}

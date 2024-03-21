package com.kickoff.domain.team.league.game;

import com.kickoff.domain.team.Score;
import com.kickoff.domain.team.league.LeagueTeam;
import com.kickoff.domain.team.league.Season;
import com.kickoff.domain.team.league.game.player.LeagueGamePlayer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private List<LeagueGamePlayer> gamePlayerList = new ArrayList<>();

    @Builder
    public LeagueGame(
            Long leagueGameId,
            LocalDateTime gameDate,
            int count,
            LeagueTeam away,
            LeagueTeam home,
            Score score,
            LeagueGameStatus leagueGameStatus,
            Season season,
            List<LeagueGamePlayer> gamePlayerList
    ) {
        this.leagueGameId = leagueGameId;
        this.gameDate = gameDate;
        this.count = count;
        this.away = away;
        this.home = home;
        this.score = score;
        this.leagueGameStatus = leagueGameStatus;
        this.season = season;
        setGamePlayerList(gamePlayerList);
    }

    private void setGamePlayerList(List<LeagueGamePlayer> gamePlayerList)
    {
        Map<LeagueTeam, List<LeagueGamePlayer>> maps = gamePlayerList.stream()
                .collect(Collectors.groupingBy(s -> s.getPlayer().getLeagueTeam()));

        if (maps.keySet().size() >= 3) {
            throw new IllegalArgumentException("팀 두개 초과");
        }

        for (Map.Entry<LeagueTeam, List<LeagueGamePlayer>> entry : maps.entrySet())
        {
            List<LeagueGamePlayer> teams = entry.getValue();

            // todo : 모든 포지션의 사람이 있는지...


            /// todo 키퍼는 1명인지, 나머지는 10명인지...

        }
        this.gamePlayerList = gamePlayerList;
    }
}

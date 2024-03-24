package com.kickoff.domain.team.league.game;

import com.kickoff.domain.player.PlayerPosition;
import com.kickoff.domain.team.Score;
import com.kickoff.domain.team.league.LeagueTeam;
import com.kickoff.domain.team.league.Season;
import com.kickoff.domain.team.league.game.player.LeagueGamePlayer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.kickoff.domain.player.PlayerPosition.*;

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
        setHomePlayers(homePlayers);
        setAwayPlayers(awayPlayers);
    }

        
    private void setHomePlayers(List<LeagueGamePlayer> homePlayers)
    {


        int keeper = 0;
        int position = 0;
        int member = 0;

        for (LeagueGamePlayer player : homePlayers)
        {
            member += 1;
            if(player.getPosition()== KEEPER){
                keeper += 1;
            }else if(player.getPosition() == FORWARD){
                position += 1;
            }else if(player.getPosition() == DEFENDER){
                position += 1;
            }else if(player.getPosition() == MID_FIELDER){
                position += 1;
            }
        }
        if(keeper != 1|| position < 3){
            throw new IllegalArgumentException("골키퍼 1명이 아니거나 멤버가 11명이 아님");
        }
        this.homePlayers = homePlayers;
    }

    private void setAwayPlayers(List<LeagueGamePlayer> awayPlayers)
    {
        int keeper = 0;
        int position = 0;
        int member = 0;


        for (LeagueGamePlayer player : awayPlayers)
        {
            member += 1;
            if(player.getPosition()== KEEPER){
                keeper += 1;
            }else if(player.getPosition() == FORWARD){
                position += 1;
            }else if(player.getPosition() == DEFENDER){
                position += 1;
            }else if(player.getPosition() == MID_FIELDER){
                position += 1;
            }
        }
//         || member != 11
        if(keeper != 1|| position < 3){
                throw new IllegalArgumentException("골키퍼 1명이 아니거나 멤버가 11명이 아님");
            }
        this.awayPlayers = awayPlayers;
    }


}

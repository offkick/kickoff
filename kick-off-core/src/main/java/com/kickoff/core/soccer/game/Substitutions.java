package com.kickoff.core.soccer.game;

import com.kickoff.core.soccer.game.player.LeagueGamePlayer;
import com.kickoff.core.soccer.league.LeagueTeam;
import com.kickoff.core.soccer.player.Player;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name = "game_substitutions")
@Entity
public class Substitutions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int minute;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_out_id")
    private Player playerOut;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_in_id")
    private Player playerIn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "substitution_team_id")
    private LeagueTeam substitutionTeam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "league_game_id")
    private LeagueGame leagueGame;


    @Builder
    public Substitutions(int minute, Player playerOut, Player playerIn, LeagueTeam substitutionTeam, LeagueGame leagueGame) {
        this.minute = minute;
        this.playerOut = playerOut;
        this.playerIn = playerIn;
        this.substitutionTeam = substitutionTeam;
        this.leagueGame = leagueGame;
    }
}

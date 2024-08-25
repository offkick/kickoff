package com.kickoff.core.soccer.team;

import com.kickoff.core.soccer.player.Player;
import com.kickoff.core.soccer.team.league.LeagueTeam;
import com.kickoff.core.soccer.team.league.game.LeagueGame;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private Player player;

    private int minute;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home")
    private LeagueTeam scoredTeam;


    private GoalType type;

    @Builder
    public Goal(Player player, int minute, LeagueTeam scoredTeam, LeagueTeam awayTeam, GoalType type) {
        this.player = player;
        this.minute = minute;
        this.scoredTeam = scoredTeam;
        this.type = type;
    }
}

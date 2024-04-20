package com.kickoff.domain.soccer.player;

import com.kickoff.domain.team.league.LeagueTeam;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playerId;

    private String national;

    private String playerName;

    @Enumerated(EnumType.STRING)
    private PlayerPosition position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "league_team_id")
    private LeagueTeam leagueTeam;

    @Builder
    public Player(Long playerId, String national, String playerName, PlayerPosition position, LeagueTeam leagueTeam)
    {
        this.playerId = playerId;
        this.national = national;
        this.playerName = playerName;
        this.position = position;
        this.leagueTeam = leagueTeam;
    }
}

package com.kickoff.core.soccer.player;

import com.kickoff.core.soccer.team.league.LeagueTeam;
import com.kickoff.core.soccer.team.league.Season;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.HashSet;
import java.util.Set;


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

    private String birth;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id")
    private Season season;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PlayerImage> playerImages = new HashSet<>();


    @Builder
    public Player(
            Long playerId,
            String national,
            String playerName,
            PlayerPosition position,
            LeagueTeam leagueTeam,
            String birth,
            Season season
    ) {
        this.playerId = playerId;
        this.national = national;
        this.playerName = playerName;
        this.position = position;
        this.leagueTeam = leagueTeam;
        this.birth = birth;
        this.season = season;
    }
}

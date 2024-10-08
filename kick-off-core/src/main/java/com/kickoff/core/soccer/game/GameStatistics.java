package com.kickoff.core.soccer.game;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Entity
@AllArgsConstructor
@Builder
public class GameStatistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "league_game_id")
    private LeagueGame leagueGame;

    private String type;
    private String cornerKicks;
    private String freeKicks;
    private String goalKicks;
    private String offsides;
    private String fouls;
    private String ballPossession;
    private String saves;
    private String throwIns;
    private String shots;
    private String shotsOnGoal;
    private String shotsOffGoal;
    private String yellowCards;
    private String yellowRedCards;
    private String redCards;
}

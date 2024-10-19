package com.kickoff.core.soccer.player;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Entity
@Getter
@NoArgsConstructor
public class LeaguePlayerRank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leaguePlayerRankId;

    @Column(nullable = false)
    private Long playerId;
    private String section;
    private String position;
    @Column(nullable = false)
    private Long leagueTeamId;
    private Long matchDay;
    private Long goals;
    private Long assists;
    private Long penaltie;
}

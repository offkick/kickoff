package com.kickoff.core.soccer.team.league;

import com.kickoff.core.common.National;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class League {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leagueId;

    private String leagueName;

    @Enumerated(EnumType.STRING)
    private National national;

    private String tier;

    @Builder
    public League(
            Long leagueId,
            String leagueName,
            National national,
            String tier
    ) {
        this.leagueId = leagueId;
        this.leagueName = leagueName;
        this.national = national;
        this.tier = tier;
    }
}

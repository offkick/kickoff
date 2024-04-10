package com.kickoff.domain.team.league;

import com.kickoff.domain.common.National;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

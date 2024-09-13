package com.kickoff.core.soccer.team.league;

import com.kickoff.core.BaseEntity;
import com.kickoff.core.common.National;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class League extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leagueId;

    private String leagueName;

    @Enumerated(EnumType.STRING)
    private National national;

    private String emblem;
    private String tier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id")
    private Season season;

    @Column(columnDefinition = "boolean default false")
    private Boolean isDeleted;

    private Long winner;

    @Builder
    public League(
            Long leagueId,
            String leagueName,
            National national,
            String tier,
            String emblem,
            Season season,
            Long winner) {
        this.leagueId = leagueId;
        this.leagueName = leagueName;
        this.national = national;
        this.tier = tier;
        this.season = season;
        this.emblem = emblem;
        isDeleted = Boolean.FALSE;
        this.winner = winner;
    }

    public void delete()
    {
        if (!isDeleted)
        {
            this.isDeleted = true;
        }
    }
}

package com.kickoff.core.soccer.league;

import com.kickoff.core.BaseEntity;
import com.kickoff.core.soccer.team.TeamType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@ToString
public class LeagueTeam extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leagueTeamId;

    private String leagueTeamName;

    @Enumerated(EnumType.STRING)
    private TeamType teamType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "league_id")
    private League league;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id")
    private Season season;

    private String logo;

    @Column(columnDefinition = "boolean default false")
    private Boolean isDeleted;

    @Builder
    public LeagueTeam(
            Long leagueTeamId,
            String leagueTeamName,
            TeamType teamType,
            String logo,
            League league,
            Season season) {
        this.leagueTeamId = leagueTeamId;
        this.leagueTeamName = leagueTeamName;
        this.teamType = teamType;
        this.league = league;
        this.logo = logo;
        this.season = season;
        isDeleted = Boolean.FALSE;
    }

    public void delete()
    {
        if (!isDeleted)
        {
            this.isDeleted = true;
        }
    }
}

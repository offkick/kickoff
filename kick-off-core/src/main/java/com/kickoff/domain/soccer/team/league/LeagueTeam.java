package com.kickoff.domain.soccer.team.league;

import com.kickoff.domain.soccer.team.TeamType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Getter
@ToString
public class LeagueTeam {

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

    @Builder
    public LeagueTeam(
            Long leagueTeamId,
            String leagueTeamName,
            TeamType teamType,
            League league,
            Season season) {
        this.leagueTeamId = leagueTeamId;
        this.leagueTeamName = leagueTeamName;
        this.teamType = teamType;
        this.league = league;
        this.season = season;
    }
}

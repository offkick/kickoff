package com.kickoff.core.soccer.team.league;

import com.kickoff.core.soccer.team.TeamType;
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

    @Builder
    public LeagueTeam(
            Long leagueTeamId,
            String leagueTeamName,
            TeamType teamType,
            League league
    ) {
        this.leagueTeamId = leagueTeamId;
        this.leagueTeamName = leagueTeamName;
        this.teamType = teamType;
        this.league = league;
    }
}

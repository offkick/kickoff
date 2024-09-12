package com.kickoff.core.soccer.team.external;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class ExternalTeamIdMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long externalId;

    private Long teamId;
    private Long externalTeamId;
    private String season;
    @Enumerated(EnumType.STRING)
    private ExternalApiName externalApiName;

    @Builder
    public ExternalTeamIdMapping(Long externalId, Long leagueTeamId, Long externalTeamId, String season, ExternalApiName externalApiName) {
        this.externalId = externalId;
        this.teamId = leagueTeamId;
        this.externalTeamId = externalTeamId;
        this.season = season;
        this.externalApiName = externalApiName;
    }
}

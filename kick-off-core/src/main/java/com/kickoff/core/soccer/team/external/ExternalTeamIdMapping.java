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

    @Enumerated(EnumType.STRING)
    private ExternalApiName externalApiName;

    @Builder
    public ExternalTeamIdMapping(Long externalId, Long teamId, Long externalTeamId, ExternalApiName externalApiName) {
        this.externalId = externalId;
        this.teamId = teamId;
        this.externalTeamId = externalTeamId;
        this.externalApiName = externalApiName;
    }
}

package com.kickoff.core.soccer.team.external;

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
public class ExternalPlayerIdMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long playerId;
    private Long externalPlayerId;
    private String season;

    @Builder
    public ExternalPlayerIdMapping(Long id, Long playerId, Long externalPlayerId, String season)
    {
        this.id = id;
        this.playerId = playerId;
        this.externalPlayerId = externalPlayerId;
        this.season = season;
    }
}

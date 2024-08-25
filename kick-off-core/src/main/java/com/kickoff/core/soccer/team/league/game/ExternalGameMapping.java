package com.kickoff.core.soccer.team.league.game;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class ExternalGameMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long gameId;
    private Long externalGameId;

    @Builder
    public ExternalGameMapping(Long id, Long gameId, Long externalGameId) {
        this.id = id;
        this.gameId = gameId;
        this.externalGameId = externalGameId;
    }
}

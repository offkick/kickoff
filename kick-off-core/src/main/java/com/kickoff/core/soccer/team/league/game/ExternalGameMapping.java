package com.kickoff.core.soccer.team.league.game;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "external_game_mapping", indexes = @Index(name = "idx_game_id", columnList = "game_id, external_game_id"))
public class ExternalGameMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "game_id")
    private Long gameId;

    @Column(name = "external_game_id")
    private Long externalGameId;

    @Builder
    public ExternalGameMapping(Long id, Long gameId, Long externalGameId)
    {
        this.id = id;
        this.gameId = gameId;
        this.externalGameId = externalGameId;
    }
}

package com.kickoff.domain.player;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playerId;
    private String national;
    private String playerName;

    @Enumerated(EnumType.STRING)
    private PlayerPosition position;
}

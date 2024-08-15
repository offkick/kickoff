package com.kickoff.core.soccer.player;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Embeddable
@NoArgsConstructor
public class Contract {
    private String start;
    private String until;
}

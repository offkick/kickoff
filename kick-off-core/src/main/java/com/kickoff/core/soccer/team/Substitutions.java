package com.kickoff.core.soccer.team;

import com.kickoff.core.soccer.player.Player;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Embeddable
public class Substitutions {
    private int minute;
    private Player playerOut;
    private Player playerI;
}

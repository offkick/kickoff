package com.kickoff.core.soccer.game;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CardType {
    YELLOW("YELLOW"),
    RED("RED"),
    YELLOW_RED("YELLOW_RED"),

    ;
    private final String type;
}

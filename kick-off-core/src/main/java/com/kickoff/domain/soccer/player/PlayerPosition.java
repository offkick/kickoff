package com.kickoff.domain.soccer.player;

import jakarta.annotation.Nullable;
import lombok.Getter;

@Getter
public enum PlayerPosition {
    FORWARD("Offence"),
    DEFENDER("Defence"),
    MID_FIELDER("Midfield"),
    KEEPER("Goalkeeper"),
    ;

    PlayerPosition(String name)
    {
        this.name = name;
    }

    private final String name;

    @Nullable
    public static PlayerPosition of(String position)
    {
        PlayerPosition[] values = PlayerPosition.values();

        for (PlayerPosition playerPosition : values)
        {
            if (position.equals(playerPosition.getName())) {
                return playerPosition;
            }
        }

        return null;
    }
}

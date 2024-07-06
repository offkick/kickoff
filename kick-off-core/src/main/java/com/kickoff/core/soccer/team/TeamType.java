package com.kickoff.core.soccer.team;


public enum TeamType {
    NATIONAL,
    LEAGUE,
    ;
    public static TeamType of(String competition) {
        return switch (competition) {
            case "PL" -> TeamType.LEAGUE;
            default -> TeamType.NATIONAL;
        };
    }
}

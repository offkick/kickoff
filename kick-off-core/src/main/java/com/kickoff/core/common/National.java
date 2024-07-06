package com.kickoff.core.common;

public enum National {
    KOREA,
    ENGLAND,
    JAPAN,
    BRAZIL,
    OTHER
    ;
    public static National of(String competition)
    {
        // PL -> ENGLAND
        return switch (competition) {
            case "PL" -> National.ENGLAND;
            default -> National.OTHER;
        };
    }
}

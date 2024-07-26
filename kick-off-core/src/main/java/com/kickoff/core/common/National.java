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
        return switch (competition)
        {
            case "PL" -> National.ENGLAND;
            default -> National.OTHER;
        };
    }
}

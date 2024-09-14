package com.kickoff.batch.jobs.api.football;

import lombok.experimental.UtilityClass;

@UtilityClass
public class FootballDataUtil {

    public static String getCompetition(String leagueName)
    {
        return switch (leagueName) {
            case "PL" -> "PL";
            case "LALIGA" -> "PD";
            case "LEAGUE1" -> "FL1";
            case "BUNDESLIGA" -> "ABL";
            default -> throw new IllegalArgumentException("지원 하지 않는 코드");
        };
    }
}

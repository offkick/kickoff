package com.kickoff.batch.config.feign.api.temp;

import com.kickoff.core.soccer.game.GameStatistics;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Statistics {
    private String corner_kicks;
    private String free_kicks;
    private String goal_kicks;
    private String offsides;
    private String fouls;
    private String ball_possession;
    private String saves;
    private String throw_ins;
    private String shots;
    private String shots_on_goal;
    private String shots_off_goal;
    private String yellow_cards;
    private String yellow_red_cards;
    private String red_cards;

    public static GameStatistics of(Statistics statistics, String type)
    {
        return GameStatistics.builder()
                .type(type)
                .cornerKicks(statistics.getCorner_kicks())
                .freeKicks(statistics.getFree_kicks())
                .goalKicks(statistics.getGoal_kicks())
                .offsides(statistics.offsides)
                .fouls(statistics.fouls)
                .ballPossession(statistics.ball_possession)
                .saves(statistics.saves)
                .throwIns(statistics.throw_ins)
                .shots(statistics.shots)
                .shotsOnGoal(statistics.shots_on_goal)
                .shotsOffGoal(statistics.shots_off_goal)
                .yellowCards(statistics.yellow_cards)
                .yellowRedCards(statistics.yellow_red_cards)
                .redCards(statistics.red_cards)
                .build();
    }
}

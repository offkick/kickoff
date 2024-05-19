package com.kickoff.batch.config.feign;

import com.kickoff.batch.config.feign.api.SoccerApiFeign;
import com.kickoff.batch.config.feign.api.dto.CompetitionTeamsResponse;
import com.kickoff.batch.config.feign.api.dto.MatchesFeignResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SoccerApiFeignTest {

    @Autowired
    SoccerApiFeign soccerApiFeign;

    @Test
    public void test()
    {
        MatchesFeignResponse matches = soccerApiFeign.getLatestClMatch();
        System.out.println(matches);

        for (MatchesFeignResponse.Match match : matches.matches())
        {
            System.out.println("match = " + match);
        }
    }

    @Test
    public void getCompetitionTeams()
    {
        CompetitionTeamsResponse pl = soccerApiFeign.getCompetitionTeams("2023");
        System.out.println(pl);
    }
}
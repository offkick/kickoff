package com.kickoff.batch.config.feign;

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
        MatchesResponse matches = soccerApiFeign.matches();
        System.out.println(matches);

        for (MatchesResponse.Match match : matches.matches())
        {
            System.out.println("match = " + match);
        }
    }
}
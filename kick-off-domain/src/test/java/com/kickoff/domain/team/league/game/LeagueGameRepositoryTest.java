package com.kickoff.domain.team.league.game;

import com.kickoff.domain.TestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = TestConfiguration.class)
@DataJpaTest
public class LeagueGameRepositoryTest {

    @Autowired
    LeagueGameRepository leagueGameRepository;

    @Test
    public void league(){

    }
}

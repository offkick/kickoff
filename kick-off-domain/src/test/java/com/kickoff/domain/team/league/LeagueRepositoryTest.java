package com.kickoff.domain.team.league;

import com.kickoff.domain.TestConfiguration;
import com.kickoff.domain.common.National;
import com.kickoff.domain.soccer.team.league.League;
import com.kickoff.domain.soccer.team.league.LeagueRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = TestConfiguration.class)
@DataJpaTest
public class LeagueRepositoryTest {
    @Autowired
    private LeagueRepository leagueRepository;

    @Test
    public void save()
    {
        League league = leagueRepository.save(
                League.builder()
                        .leagueName("k-league")
                        .tier("K리그1")
                        .national(National.KOREA)
                        .build()
        );
        assertThat(league.getLeagueId()).isNotNull();
    }



}

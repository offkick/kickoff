package com.kickoff.core.team.league;

import com.kickoff.core.TestConfiguration;
import com.kickoff.core.common.National;
import com.kickoff.core.soccer.team.league.League;
import com.kickoff.core.soccer.team.league.LeagueRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("domain")
@ContextConfiguration(classes = TestConfiguration.class)
@SpringBootTest
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

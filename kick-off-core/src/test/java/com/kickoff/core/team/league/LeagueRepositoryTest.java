package com.kickoff.core.team.league;

import com.kickoff.core.TestConfiguration;
import com.kickoff.core.common.National;
import com.kickoff.core.soccer.league.League;
import com.kickoff.core.soccer.league.LeagueRepository;
import com.kickoff.core.soccer.league.dto.LatestLeagueDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("core")
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

    @Test
    public void findLatestLeagueTest()
    {
        League pl = leagueRepository.save(
                League.builder()
                        .leagueName("PL")
                        .startDate("2024-04-02")
                        .national(National.ENGLAND)
                        .build()
        );
        League pl2 = leagueRepository.save(
                League.builder()
                        .leagueName("PL")
                        .startDate("2023-04-02")
                        .national(National.ENGLAND)
                        .build()
        );
        League pl3 = leagueRepository.save(
                League.builder()
                        .leagueName("PD")
                        .startDate("2023-04-02")
                        .national(National.SPAIN)
                        .build()
        );
        List<LatestLeagueDTO> latestLeague = leagueRepository.findLatestLeague();
        System.out.println("latestLeague ====" + latestLeague);
        assertThat(latestLeague).hasSize(2);

    }



}

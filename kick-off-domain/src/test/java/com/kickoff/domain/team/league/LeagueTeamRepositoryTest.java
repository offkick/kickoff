package com.kickoff.domain.team.league;

import com.kickoff.domain.TestConfiguration;
import com.kickoff.domain.soccer.team.TeamType;
import com.kickoff.domain.soccer.team.league.LeagueTeamRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("domain")
@ContextConfiguration(classes = TestConfiguration.class)
@SpringBootTest
public class LeagueTeamRepositoryTest {

    @Autowired
    private LeagueTeamRepository leagueTeamRepository;

    @Test
    public void save()
    {
        com.kickoff.domain.team.league.LeagueTeam leagueTeam = leagueTeamRepository.save(
                com.kickoff.domain.team.league.LeagueTeam.builder()
                        .leagueTeamName("mancity")
                        .teamType(TeamType.LEAGUE)
                        .build()
        );

        assertThat(leagueTeam).isNotNull();
    }
}

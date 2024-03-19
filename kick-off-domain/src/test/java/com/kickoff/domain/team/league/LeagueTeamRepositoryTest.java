package com.kickoff.domain.team.league;

import com.kickoff.domain.TestConfiguration;
import com.kickoff.domain.team.TeamType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = TestConfiguration.class)
@DataJpaTest
public class LeagueTeamRepositoryTest {
    @Autowired
    private LeagueTeamRepository leagueTeamRepository;

    @Test
    public void save()
    {
        LeagueTeam leagueTeam = leagueTeamRepository.save(
                LeagueTeam.builder()
                        .leagueTeamName("mancity")
                        .teamType(TeamType.LEAGUE)
                        .build()
        );

        assertThat(leagueTeam).isNotNull();
    }
}

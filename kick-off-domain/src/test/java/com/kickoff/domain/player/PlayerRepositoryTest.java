package com.kickoff.domain.player;

import com.kickoff.domain.TestConfiguration;
import com.kickoff.domain.team.TeamType;
import com.kickoff.domain.team.league.LeagueTeam;
import com.kickoff.domain.team.league.LeagueTeamRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = TestConfiguration.class)
@DataJpaTest
public class PlayerRepositoryTest {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private LeagueTeamRepository leagueTeamRepository;

    @Test
    public void save()
    {
        // given
        LeagueTeam leagueTeam = leagueTeamRepository.save(
                LeagueTeam.builder()
                        .leagueTeamName("man-city")
                        .teamType(TeamType.LEAGUE)
                        .build()
        );

        Player player = playerRepository.save(
                Player.builder()
                        .playerName("kdb")
                        .leagueTeam(leagueTeam)
                        .position(PlayerPosition.MID_FIELDER)
                        .build()
        );

        // then
        assertThat(player.getPlayerId()).isNotNull();
    }
}

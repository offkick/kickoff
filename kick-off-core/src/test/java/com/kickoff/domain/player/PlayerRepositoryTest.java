package com.kickoff.domain.player;

import com.kickoff.domain.TestConfiguration;
import com.kickoff.domain.soccer.player.Player;
import com.kickoff.domain.soccer.player.PlayerPosition;
import com.kickoff.domain.soccer.player.PlayerRepository;
import com.kickoff.domain.soccer.team.TeamType;
import com.kickoff.domain.soccer.team.league.LeagueTeamRepository;
import com.kickoff.domain.team.league.LeagueTeam;
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

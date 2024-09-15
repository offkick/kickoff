package com.kickoff.core.player;

import com.kickoff.core.TestConfiguration;
import com.kickoff.core.soccer.player.Player;
import com.kickoff.core.soccer.player.PlayerPosition;
import com.kickoff.core.soccer.player.PlayerRepository;
import com.kickoff.core.soccer.team.TeamType;
import com.kickoff.core.soccer.league.LeagueTeam;
import com.kickoff.core.soccer.league.LeagueTeamRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("core")
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

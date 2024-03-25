package com.kickoff.domain.team.league.game;

import com.kickoff.domain.TestConfiguration;
import com.kickoff.domain.player.Player;
import com.kickoff.domain.player.PlayerPosition;
import com.kickoff.domain.player.PlayerRepository;
import com.kickoff.domain.team.Score;
import com.kickoff.domain.team.ScoreRepository;
import com.kickoff.domain.team.TeamType;
import com.kickoff.domain.team.league.LeagueTeam;
import com.kickoff.domain.team.league.LeagueTeamRepository;
import com.kickoff.domain.team.league.Season;
import com.kickoff.domain.team.league.SeasonRepository;
import com.kickoff.domain.team.league.game.player.LeagueGamePlayer;
import com.kickoff.domain.team.league.game.player.LeagueGamePlayerRepository;
import com.kickoff.domain.team.league.game.player.LeagueGamePlayerStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = TestConfiguration.class)
@DataJpaTest
public class LeagueGameRepositoryTest {

    @Autowired
    private LeagueGameRepository leagueGameRepository;

    @Autowired
    private LeagueGamePlayerRepository leagueGamePlayerRepository;

    @Autowired
    private LeagueTeamRepository leagueTeamRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private PlayerRepository playerRepository;

    private static LeagueGamePlayer createLeagueGamePlayer(
            Player player,
            PlayerPosition position
    ) {
        return LeagueGamePlayer.builder()
                .playedTime(90)
                .status(LeagueGamePlayerStatus.STARTING)
                .position(position)
                .player(player)
                .build();
    }

    private static Player createPlayer(String name, LeagueTeam leagueTeam,  PlayerPosition position)
    {
        return Player.builder()
                .playerName(name)
                .leagueTeam(leagueTeam)
                .position(position)
                .build();
    }

    @Test
    public void league()
    {
        LeagueTeam homeTeam = leagueTeamRepository.save(
                LeagueTeam.builder()
                        .leagueTeamName("mancity")
                        .teamType(TeamType.LEAGUE)
                        .build()
        );

        LeagueTeam awayTeam = leagueTeamRepository.save(
                LeagueTeam.builder()
                        .leagueTeamName("tottenham")
                        .teamType(TeamType.LEAGUE)
                        .build()
        );

        Score score = scoreRepository.save(
                Score.builder()
                        .homeScore(1)
                        .awayScore(3)
                        .build()
        );

        Season season = seasonRepository.save(
                Season.builder()
                        .season("22-23")
                        .build()
        );

        String [] playerNames = new String[]{
                "kdb",
                "ederson",
                "dias",
                "sonny",
                "royal",
                "maddison",
                "vicario",
                "이강인",
                "김민재",
                "황희찬",
                "조현우"
        };

        PlayerPosition [] playerPositions = new PlayerPosition[]{
                PlayerPosition.MID_FIELDER,
                PlayerPosition.MID_FIELDER,
                PlayerPosition.MID_FIELDER,
                PlayerPosition.MID_FIELDER,
                PlayerPosition.MID_FIELDER,
                PlayerPosition.MID_FIELDER,
                PlayerPosition.MID_FIELDER,
                PlayerPosition.MID_FIELDER,
                PlayerPosition.DEFENDER,
                PlayerPosition.FORWARD,
                PlayerPosition.KEEPER,
        };

        List<Player> homePlayers = new ArrayList<>();
        List<Player> awayPlayers = new ArrayList<>();
        List<LeagueGamePlayer> homeLeagueGamePlayers = new ArrayList<>();
        List<LeagueGamePlayer> awayLeagueGamePlayers = new ArrayList<>();

        for (int i = 0; i < playerNames.length; i++)
        {
            Player homePlayer = createPlayer(playerNames[i], homeTeam, playerPositions[i]);
            playerRepository.save(homePlayer);
            Player awayPlayer = createPlayer(playerNames[i], awayTeam, playerPositions[i]);
            playerRepository.save(awayPlayer);
            homePlayers.add(homePlayer);
            awayPlayers.add(awayPlayer);
            homeLeagueGamePlayers.add(createLeagueGamePlayer(homePlayer, homePlayer.getPosition()));
            awayLeagueGamePlayers.add(createLeagueGamePlayer(awayPlayer, awayPlayer.getPosition()));
        }

        LeagueGame leagueGame = leagueGameRepository.save(
                LeagueGame.builder()
                        .leagueGameStatus(LeagueGameStatus.GAMING)
                        .gameDate(LocalDate.of(2024, 11, 24).atStartOfDay())
                        .count(2)
                        .away(awayTeam)
                        .home(homeTeam)
                        .score(score)
                        .season(season)
                        .homePlayers(homeLeagueGamePlayers)
                        .awayPlayers(awayLeagueGamePlayers)
                        .build()
        );
        assertThat(leagueGame.getLeagueGameId()).isNotNull();
        assertThat(leagueGame.getAwayPlayers().size()).isEqualTo(11);
        assertThat(leagueGame.getScore().getAwayScore()).isEqualTo(3);
    }
}

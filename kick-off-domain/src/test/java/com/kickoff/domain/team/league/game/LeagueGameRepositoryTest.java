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

        Player player = playerRepository.save(
                Player.builder()
                        .playerName("kdb")
                        .leagueTeam(homeTeam)
                        .position(PlayerPosition.MID_FIELDER)
                        .build()
        );

        Player player1 = playerRepository.save(
                Player.builder()
                        .playerName("ederson")
                        .leagueTeam(homeTeam)
                        .position(PlayerPosition.KEEPER)
                        .build()
        );
        Player player2 = playerRepository.save(
                Player.builder()
                        .playerName("dias")
                        .leagueTeam(homeTeam)
                        .position(PlayerPosition.DEFENDER)
                        .build()
        );
        Player player3 = playerRepository.save(
                Player.builder()
                        .playerName("haaland")
                        .leagueTeam(homeTeam)
                        .position(PlayerPosition.FORWARD)
                        .build()
        );

        Player player4 = playerRepository.save(
                Player.builder()
                        .playerName("sonny")
                        .leagueTeam(homeTeam)
                        .position(PlayerPosition.FORWARD)
                        .build()
        );

        Player player5 = playerRepository.save(
                Player.builder()
                        .playerName("royal")
                        .leagueTeam(homeTeam)
                        .position(PlayerPosition.DEFENDER)
                        .build()
        );

        Player player6 = playerRepository.save(
                Player.builder()
                        .playerName("maddison")
                        .leagueTeam(homeTeam)
                        .position(PlayerPosition.MID_FIELDER)
                        .build()
        );

        Player player7 = playerRepository.save(
                Player.builder()
                        .playerName("vicario")
                        .leagueTeam(homeTeam)
                        .position(PlayerPosition.KEEPER)
                        .build()
        );

        LeagueGamePlayer homePlayer = leagueGamePlayerRepository.save(
                LeagueGamePlayer.builder()
                        .playedTime(90)
                        .status(LeagueGamePlayerStatus.STARTING)
                        .position(PlayerPosition.MID_FIELDER)
                        .player(player)
                        .build()
        );

        LeagueGamePlayer homePlayer1 = leagueGamePlayerRepository.save(
                LeagueGamePlayer.builder()
                        .playedTime(90)
                        .status(LeagueGamePlayerStatus.STARTING)
                        .position(PlayerPosition.KEEPER)
                        .player(player1)
                        .build()
        );

        LeagueGamePlayer homePlayer2 = leagueGamePlayerRepository.save(
                LeagueGamePlayer.builder()
                        .playedTime(90)
                        .status(LeagueGamePlayerStatus.STARTING)
                        .position(PlayerPosition.DEFENDER)
                        .player(player2)
                        .build()
        );

        LeagueGamePlayer homePlayer3 = leagueGamePlayerRepository.save(
                LeagueGamePlayer.builder()
                        .playedTime(90)
                        .status(LeagueGamePlayerStatus.STARTING)
                        .position(PlayerPosition.FORWARD)
                        .player(player3)
                        .build()
        );


        LeagueGamePlayer awayPlayer = leagueGamePlayerRepository.save(
                LeagueGamePlayer.builder()
                        .playedTime(90)
                        .status(LeagueGamePlayerStatus.STARTING)
                        .position(PlayerPosition.FORWARD)
                        .player(player4)
                        .build()
        );

        LeagueGamePlayer awayPlayer1 = leagueGamePlayerRepository.save(
                LeagueGamePlayer.builder()
                        .playedTime(90)
                        .status(LeagueGamePlayerStatus.STARTING)
                        .position(PlayerPosition.DEFENDER)
                        .player(player5)
                        .build()
        );

        LeagueGamePlayer awayPlayer2 = leagueGamePlayerRepository.save(
                LeagueGamePlayer.builder()
                        .playedTime(90)
                        .status(LeagueGamePlayerStatus.STARTING)
                        .position(PlayerPosition.MID_FIELDER)
                        .player(player6)
                        .build()
        );

        LeagueGamePlayer awayPlayer3 = leagueGamePlayerRepository.save(
                LeagueGamePlayer.builder()
                        .playedTime(90)
                        .status(LeagueGamePlayerStatus.STARTING)
                        .position(PlayerPosition.KEEPER)
                        .player(player7)
                        .build()
        );

        List<LeagueGamePlayer> homePlayers = List.of(homePlayer,homePlayer1,homePlayer2,homePlayer3);
        List<LeagueGamePlayer> awayPlayers = List.of(awayPlayer,awayPlayer1,awayPlayer2,awayPlayer3);

        LeagueGame leagueGame = leagueGameRepository.save(
                LeagueGame.builder()
                        .leagueGameStatus(LeagueGameStatus.GAMING)
                        .gameDate(LocalDate.of(2024, 11, 24).atStartOfDay())
                        .count(2)
                        .away(awayTeam)
                        .home(homeTeam)
                        .score(score)
                        .season(season)
                        .homePlayers(homePlayers)
                        .awayPlayers(awayPlayers)
                        .build()
        );

        assertThat(leagueGame.getAwayPlayers().size()).isEqualTo(4);
        assertThat(leagueGame.getHomePlayers().size()).isEqualTo(4);
        assertThat(leagueGame.getLeagueGameId()).isNotNull();

    }
}

package com.kickoff.core.team.league.game;

import com.kickoff.core.TestConfiguration;
import com.kickoff.core.common.National;
import com.kickoff.core.soccer.league.*;
import com.kickoff.core.soccer.player.Player;
import com.kickoff.core.soccer.player.PlayerPosition;
import com.kickoff.core.soccer.player.PlayerRepository;
import com.kickoff.core.soccer.team.Score;
import com.kickoff.core.soccer.team.TeamType;
import com.kickoff.core.soccer.league.game.LeagueGame;
import com.kickoff.core.soccer.league.game.LeagueGameRepository;
import com.kickoff.core.soccer.league.game.LeagueGameStatus;
import com.kickoff.core.soccer.league.game.dto.GameSearchCondition;
import com.kickoff.core.soccer.league.game.player.LeagueGamePlayer;
import com.kickoff.core.soccer.league.game.player.LeagueGamePlayerRepository;
import com.kickoff.core.soccer.league.game.player.LeagueGamePlayerStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ActiveProfiles("core")
@ContextConfiguration(classes = TestConfiguration.class)
@SpringBootTest
public class LeagueGameQuerydslRepositoryTest {

    @Autowired
    private LeagueGameRepository leagueGameRepository;

    @Autowired
    private LeagueGamePlayerRepository leagueGamePlayerRepository;

    @Autowired
    private LeagueTeamRepository leagueTeamRepository;

    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private PlayerRepository playerRepository;


    @Autowired
    private LeagueRepository leagueRepository;

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

    private static Player createPlayer(String name, LeagueTeam leagueTeam, PlayerPosition position)
    {
        return Player.builder()
                .playerName(name)
                .leagueTeam(leagueTeam)
                .position(position)
                .build();
    }

    @Test
    @Commit
    public void gameSearch()
    {
        League league =  leagueRepository.save (
                League.builder()
                .leagueName("k-league")
                .tier("K리그1")
                .national(National.KOREA)
                .build()
        );

        LeagueTeam homeTeam = leagueTeamRepository.save(
                LeagueTeam.builder()
                        .league(league)
                        .leagueTeamName("mancity")
                        .teamType(TeamType.LEAGUE)
                        .build()
        );

        LeagueTeam awayTeam = leagueTeamRepository.save(
                LeagueTeam.builder()
                        .league(league)
                        .leagueTeamName("tottenham")
                        .teamType(TeamType.LEAGUE)
                        .build()
        );

        Score score =
                Score.builder()
                        .homeScore("1")
                        .awayScore("3")
                        .build()
        ;

        Season season = seasonRepository.save(
                Season.builder()
                        .years("2023")
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

        LocalDate startDate = LocalDate.of(2024, 11, 10);
        LocalDate endDate = LocalDate.of(2024, 12, 10);
        Long leagueGameId = leagueGame.getLeagueGameId();
        GameSearchCondition condition = new GameSearchCondition(startDate, endDate, league.getLeagueId());
//        List<LeagueGame> content = leagueGameRepositoryImpl.searchGame(condition, PageRequest.of(0, 10)).getContent();
//        assertEquals(1,content.size());
    }

}

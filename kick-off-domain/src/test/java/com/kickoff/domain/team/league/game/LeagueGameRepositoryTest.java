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
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.time.LocalDateTime;

@ContextConfiguration(classes = TestConfiguration.class)
@DataJpaTest
public class LeagueGameRepositoryTest {

    @Autowired
    private LeagueGameRepository leagueGameRepository;

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

        LeagueGame leagueGame = leagueGameRepository.save(
                LeagueGame.builder()
                        .leagueGameStatus(LeagueGameStatus.GAMING)
                        .gameDate(LocalDate.of(2024, 11, 24).atStartOfDay())
                        .count(2)
                        .away(awayTeam)
                        .home(homeTeam)
                        .score(score)
                        .season(season)
                        .build()
        );



    }
}

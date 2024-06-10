package com.kickoff.batch.jobs.dailymatch;

import com.kickoff.batch.config.feign.SoccerApiFeign;
import com.kickoff.batch.config.feign.api.MatchResultResponse;
import com.kickoff.core.soccer.team.Score;
import com.kickoff.core.soccer.team.league.LeagueTeam;
import com.kickoff.core.soccer.team.league.LeagueTeamRepository;
import com.kickoff.core.soccer.team.league.game.LeagueGame;
import com.kickoff.core.soccer.team.league.game.LeagueGameRepository;
import com.kickoff.core.soccer.team.league.game.LeagueGameStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class MatchService {
    private static final Integer DIFF_DAYS = 10;

    private final SoccerApiFeign soccerApiFeign;
    private final LeagueGameRepository leagueGameRepository;
    private final LeagueTeamRepository leagueTeamRepository;

    // targetDateFrom : 2024-05-01
    // targetDateTo : 2024-06-09
    public void save(LocalDate targetDateFrom, LocalDate targetDateTo)
    {
        LocalDate currentDateTimeFrom = targetDateFrom;

        while (currentDateTimeFrom.isBefore(targetDateTo))
        {
            log.info("!!!! : {}", currentDateTimeFrom);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            MatchResultResponse matchResultResponse = soccerApiFeign.getMatchResultResponse("PL", currentDateTimeFrom.format(formatter), currentDateTimeFrom.plusDays(DIFF_DAYS).format(formatter));
            log.info("Start currentDateTimeFrom : {}", currentDateTimeFrom);
            for (MatchResultResponse.Match match : matchResultResponse.matches())
            {
                String awayTeamName = match.awayTeam().name();
                String homeTeamName = match.homeTeam().name();

                log.info("awayTeam : {}, homeTeam: {}", awayTeamName, homeTeamName);
                LeagueTeam awayTeam = leagueTeamRepository.findByLeagueTeamName(awayTeamName)
                        .orElse(LeagueTeam.builder().leagueTeamName(awayTeamName).build());

                leagueTeamRepository.save(awayTeam);

                LeagueTeam homeTeam = leagueTeamRepository.findByLeagueTeamName(homeTeamName)
                        .orElse(LeagueTeam.builder().leagueTeamName(homeTeamName).build());

                leagueTeamRepository.save(homeTeam);

                Score score = Score.builder()
                        .awayScore(match.score().fullTime().away())
                        .homeScore(match.score().fullTime().home())
                        .build();
                LeagueGame leagueGame = LeagueGame.builder()
                        .leagueGameStatus(LeagueGameStatus.END)
                        .away(awayTeam)
                        .home(homeTeam)
                        .gameDate(LocalDateTime.ofInstant(match.utcDate().toInstant(), ZoneId.systemDefault()))
                        .score(score)
                        .build();

                leagueGameRepository.save(leagueGame);
            }

            currentDateTimeFrom = currentDateTimeFrom.plusDays(10);

            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

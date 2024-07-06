package com.kickoff.batch.jobs.daily.match.service;

import com.kickoff.batch.config.feign.SoccerApiFeign;
import com.kickoff.batch.config.feign.api.MatchResultResponse;
import com.kickoff.core.soccer.team.Score;
import com.kickoff.core.soccer.team.league.LeagueTeam;
import com.kickoff.core.soccer.team.league.LeagueTeamRepository;
import com.kickoff.core.soccer.team.league.Season;
import com.kickoff.core.soccer.team.league.SeasonRepository;
import com.kickoff.core.soccer.team.league.game.LeagueGame;
import com.kickoff.core.soccer.team.league.game.LeagueGameRepository;
import com.kickoff.core.soccer.team.league.game.LeagueGameStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.time.format.DateTimeFormatter;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class DailyMatchInsertService {
    private static final Integer DIFF_DAYS = 10;
    private final SoccerApiFeign soccerApiFeign;
    private final LeagueGameRepository leagueGameRepository;
    private final LeagueTeamRepository leagueTeamRepository;
    private final SeasonRepository seasonRepository;

    /**
     * TODO ADD DESCRIPTION
     * 경기 결과르 조회해서 경기 결과 업데이트 한다. [경기 스코어 홈 팀, 어웨이 팀]
     * @param targetDateFrom
     * @param targetDateTo
     * @param competitions
     */
    public void insertMatch(LocalDate targetDateFrom, LocalDate targetDateTo, String competitions)
    {
        LocalDate currentDateTimeFrom = targetDateFrom;
        Season season = seasonRepository.findByYear("2023")
                .orElse(Season.builder().year("2023").build());

        seasonRepository.save(season);
        while (currentDateTimeFrom.isBefore(targetDateTo))
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            MatchResultResponse matchResultResponse = soccerApiFeign.getMatchResultResponse(competitions, currentDateTimeFrom.format(formatter), currentDateTimeFrom.plusDays(DIFF_DAYS).format(formatter));
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
                System.out.println("a = " + match.utcDate());

                Instant instant = match.utcDate().toInstant();
                LocalDateTime gameDate = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();

                LeagueGame leagueGame = LeagueGame.builder()
                        .leagueGameStatus(LeagueGameStatus.END)
                        .away(awayTeam)
                        .season(season)
                        .home(homeTeam)
                        .gameDate(gameDate)
                        .score(score)
                        .build();

                leagueGameRepository.save(leagueGame);
            }

            // add Date parameter
            currentDateTimeFrom = currentDateTimeFrom.plusDays(DIFF_DAYS);

            // 외부 API 시간당 제한 때문에 2초 sleep...
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

package com.kickoff.batch.jobs.game.service;

import com.kickoff.batch.config.feign.SoccerApiFeign;
import com.kickoff.batch.config.feign.api.MatchResultResponse;
import com.kickoff.core.soccer.team.Score;
import com.kickoff.core.soccer.team.external.ExternalTeamIdMapping;
import com.kickoff.core.soccer.team.external.ExternalTeamIdMappingRepository;
import com.kickoff.core.soccer.team.league.*;
import com.kickoff.core.soccer.team.league.game.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class DailyMatchResultInsertService {
    private static final Integer DIFF_DAYS = 10;

    private final SoccerApiFeign soccerApiFeign;
    private final LeagueGameRepository leagueGameRepository;
    private final LeagueTeamRepository leagueTeamRepository;
    private final SeasonRepository seasonRepository;
    private final ExternalTeamIdMappingRepository externalTeamIdMappingRepository;
    private final ExternalGameMappingRepository externalGameMappingRepository;

    /**
     * 특정 기간 내의 리그 경기를 추가 한다.
     * @param targetDateFrom
     * @param targetDateTo
     * @param competitions
     */
    public void insertMatch(LocalDate targetDateFrom, LocalDate targetDateTo, String competitions, String seasonYear)
    {
        LocalDate currentDateTimeFrom = targetDateFrom;
        Season season = seasonRepository.findByYears(seasonYear).orElse(Season.builder().years(seasonYear).build());
        seasonRepository.save(season);

        while (currentDateTimeFrom.isBefore(targetDateTo))
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            MatchResultResponse matchResultResponse = soccerApiFeign.getMatchResultResponse(competitions, currentDateTimeFrom.format(formatter), currentDateTimeFrom.plusDays(DIFF_DAYS).format(formatter));
            for (MatchResultResponse.Match match : matchResultResponse.matches())
            {
                log.info("Match : {}, {}", match.awayTeam().id(), match.homeTeam().id());
                if (externalTeamIdMappingRepository.findByExternalTeamId((long) match.awayTeam().id()).isEmpty())
                {
                    log.info("Not Found League awayTeam : {}, external Team Id : {}", match.awayTeam().name(), match.awayTeam().id());
                    continue;
                }

                if (externalTeamIdMappingRepository.findByExternalTeamId((long) match.homeTeam().id()).isEmpty())
                {
                    log.info("Not Found League home : {}, external Team Id : {}", match.homeTeam().name(), match.homeTeam().id());
                    continue;
                }

                ExternalTeamIdMapping awayTeamMapping = externalTeamIdMappingRepository.findByExternalTeamId((long) match.awayTeam().id()).orElseThrow();
                ExternalTeamIdMapping homeTeamMapping = externalTeamIdMappingRepository.findByExternalTeamId((long) match.homeTeam().id()).orElseThrow();

                log.info("finded awayTeamMapping : {}", awayTeamMapping.getTeamId());
                log.info("finded homeTeamMapping : {}", homeTeamMapping.getTeamId());

                LeagueTeam awayTeam = leagueTeamRepository.findById(awayTeamMapping.getTeamId()).orElseThrow();
                LeagueTeam homeTeam = leagueTeamRepository.findById(homeTeamMapping.getTeamId()).orElseThrow();

                Score score = Score.builder()
                        .awayScore(match.score().fullTime().away())
                        .homeScore(match.score().fullTime().home())
                        .build();

                Instant instant = match.utcDate().toInstant();
                LocalDateTime gameDate = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();

                LeagueGame leagueGame = LeagueGame.builder()
                        .leagueGameStatus(match.status().equals("FINISHED")? LeagueGameStatus.END : LeagueGameStatus.BEFORE)
                        .away(awayTeam)
                        .season(season)
                        .venue(match.venue())
                        .home(homeTeam)
                        .gameDate(gameDate)
                        .matchDay(match.matchday())
                        .score(score)
                        .build();

                leagueGameRepository.save(leagueGame);

                externalGameMappingRepository.save(ExternalGameMapping.builder()
                        .gameId(leagueGame.getLeagueGameId())
                        .externalGameId((long) match.id())
                        .build());
            }

            // add Date parameter
            currentDateTimeFrom = currentDateTimeFrom.plusDays(DIFF_DAYS);

            // 외부 API 시간당 제한 때문에 3초 sleep...
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

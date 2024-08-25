package com.kickoff.batch.jobs.game.service;

import com.kickoff.batch.config.feign.SoccerApiFeign;
import com.kickoff.batch.config.feign.api.MatchResultResponse;
import com.kickoff.core.soccer.team.Score;
import com.kickoff.core.soccer.team.TeamType;
import com.kickoff.core.soccer.team.external.ExternalApiName;
import com.kickoff.core.soccer.team.external.ExternalTeamIdMapping;
import com.kickoff.core.soccer.team.external.ExternalTeamIdMappingRepository;
import com.kickoff.core.soccer.team.league.*;
import com.kickoff.core.soccer.team.league.game.LeagueGame;
import com.kickoff.core.soccer.team.league.game.LeagueGameRepository;
import com.kickoff.core.soccer.team.league.game.LeagueGameStatus;
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
public class DailyMatchInsertService {
    private static final Integer DIFF_DAYS = 10;
    private final SoccerApiFeign soccerApiFeign;
    private final LeagueGameRepository leagueGameRepository;
    private final LeagueRepository leagueRepository;
    private final LeagueTeamRepository leagueTeamRepository;
    private final SeasonRepository seasonRepository;
    private final ExternalTeamIdMappingRepository externalTeamIdMappingRepository;

    /**
     * 경기 결과르 조회해서 경기 결과 업데이트 한다. [경기 스코어 홈 팀, 어웨이 팀]
     * @param targetDateFrom
     * @param targetDateTo
     * @param competitions
     */
    public void insertMatch(LocalDate targetDateFrom, LocalDate targetDateTo, String competitions)
    {
        LocalDate currentDateTimeFrom = targetDateFrom;
        Season season = seasonRepository.findByYears("2023")
                .orElse(Season.builder().years("2023").build());
        League league = leagueRepository.save(League.builder().tier("1").emblem("https://crests.football-data.org/PL.png").leagueName(competitions).season(season).build());
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
                        .orElse(LeagueTeam.builder().leagueTeamName(awayTeamName).teamType(TeamType.LEAGUE).logo(match.awayTeam().crest()).league(league).season(season).build());

                leagueTeamRepository.save(awayTeam);

                if (!externalTeamIdMappingRepository.findByExternalTeamIdAndTeamIdAndExternalApiName((long) match.awayTeam().id(), awayTeam.getLeagueTeamId(), ExternalApiName.FOOT_BALL_API_V1).isPresent()) {
                    externalTeamIdMappingRepository.save(
                            ExternalTeamIdMapping.builder()
                                    .externalApiName(ExternalApiName.FOOT_BALL_API_V1)
                                    .teamId(awayTeam.getLeagueTeamId())
                                    .externalTeamId((long) match.awayTeam().id())
                                    .build()
                    );
                }

                Score score = Score.builder()
                        .awayScore(match.score().fullTime().away())
                        .homeScore(match.score().fullTime().home())
                        .build();

                LeagueTeam homeTeam = leagueTeamRepository.findByLeagueTeamName(homeTeamName)
                        .orElse(
                                LeagueTeam.builder()
                                        .season(season)
                                        .league(league)
                                        .logo(match.homeTeam().crest())
                                        .teamType(TeamType.LEAGUE)
                                        .leagueTeamName(homeTeamName)
                                        .build()
                        );
                leagueTeamRepository.save(homeTeam);

                if (!externalTeamIdMappingRepository.findByExternalTeamIdAndTeamIdAndExternalApiName(
                        (long) match.homeTeam().id(),
                        homeTeam.getLeagueTeamId(),
                        ExternalApiName.FOOT_BALL_API_V1).isPresent()
                ) {
                    externalTeamIdMappingRepository.save(
                            ExternalTeamIdMapping.builder()
                                    .externalApiName(ExternalApiName.FOOT_BALL_API_V1)
                                    .teamId(homeTeam.getLeagueTeamId())
                                    .externalTeamId((long) match.homeTeam().id())
                                    .build());
                }

                Instant instant = match.utcDate().toInstant();
                LocalDateTime gameDate = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();

                LeagueGame leagueGame = LeagueGame.builder()
                        .leagueGameStatus(LeagueGameStatus.END)
                        .away(awayTeam)
                        .season(season)
                        .venue(match.venue())
                        .home(homeTeam)
                        .gameDate(gameDate)
                        .count(match.matchday())
                        .score(score)
                        .build();
                LeagueGame save = leagueGameRepository.save(leagueGame);

                // [leagueId, externalGameId] save

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

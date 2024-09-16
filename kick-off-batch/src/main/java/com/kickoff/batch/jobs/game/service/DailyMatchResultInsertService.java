package com.kickoff.batch.jobs.game.service;

import com.kickoff.batch.config.feign.SoccerApiFeign;
import com.kickoff.batch.config.feign.api.MatchResultResponse;
import com.kickoff.core.soccer.league.LeagueTeam;
import com.kickoff.core.soccer.league.LeagueTeamRepository;
import com.kickoff.core.soccer.league.game.*;
import com.kickoff.core.soccer.team.Score;
import com.kickoff.core.soccer.team.external.ExternalTeamIdMappingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class DailyMatchResultInsertService {
    private static final Integer DIFF_DAYS = 10;
    private static final long SLEEP_TIME_IN_MILLIS = 3000L;

    private final SoccerApiFeign soccerApiFeign;
    private final LeagueGameRepository leagueGameRepository;
    private final LeagueTeamRepository leagueTeamRepository;
    private final ExternalTeamIdMappingRepository externalTeamIdMappingRepository;
    private final ExternalGameMappingRepository externalGameMappingRepository;

    /**
     * 특정 기간 내의 리그 경기를 추가 한다
     *
     * @param targetDateFrom 시작 날짜
     * @param targetDateTo 종료 날짜
     * @param competitions 리그 정보
     */
    public void insertMatch(LocalDate targetDateFrom, LocalDate targetDateTo, String competitions)
    {
        LocalDate currentDateTimeFrom = targetDateFrom;

        while (currentDateTimeFrom.isBefore(targetDateTo))
        {
            processMatchesForDateRange(currentDateTimeFrom, competitions);
            currentDateTimeFrom = currentDateTimeFrom.plusDays(DIFF_DAYS);
            sleepForApiRateLimit();
        }
    }

    /**
     * 주어진 날짜 범위 내의 경기 데이터를 처리
     */
    private void processMatchesForDateRange(LocalDate currentDateTimeFrom, String competitions)
    {
        String fromDate = currentDateTimeFrom.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String toDate = currentDateTimeFrom.plusDays(DIFF_DAYS).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        int season = currentDateTimeFrom.getYear();

        MatchResultResponse matchResultResponse = soccerApiFeign.getMatchResultResponse(competitions, fromDate, toDate);
        for (MatchResultResponse.Match match : matchResultResponse.matches())
        {
            processSingleMatch(match, season);
        }
    }

    /**
     * 개별 경기를 처리
     */
    private void processSingleMatch(MatchResultResponse.Match match, int season)
    {
        log.info("Processing match: {}, {}", match.awayTeam().id(), match.homeTeam().id());

        Optional<LeagueTeam> awayTeam = findLeagueTeamByExternalId(match.awayTeam().id(), season, match.awayTeam().name());
        Optional<LeagueTeam> homeTeam = findLeagueTeamByExternalId(match.homeTeam().id(), season, match.homeTeam().name());

        if (awayTeam.isEmpty() || homeTeam.isEmpty())
        {
            return;
        }

        LocalDateTime gameDate = convertToGameDateTime(match.utcDate().toInstant());
        Score score = buildScore(match);

        if (isGameAlreadyProcessed(match.id()))
        {
            updateExistingGame(match, score);
        } else
        {
            saveNewGame(match, gameDate, score, awayTeam.get(), homeTeam.get());
        }
    }

    /**
     * 외부 팀 ID로 리그 팀을 찾음
     */
    private Optional<LeagueTeam> findLeagueTeamByExternalId(long externalTeamId, int season, String teamName)
    {
        return externalTeamIdMappingRepository.findByExternalTeamIdAndSeason(externalTeamId, String.valueOf(season))
                .map(mapping -> leagueTeamRepository.findById(mapping.getTeamId()))
                .orElseGet(() ->
                {
                    log.info("Not Found League Team: {}, External Team ID: {},  season ID: {}", teamName, externalTeamId, season);
                    return Optional.empty();
                });
    }

    /**
     * 경기를 이미 처리했는지 확인
     */
    private boolean isGameAlreadyProcessed(long matchId)
    {
        return externalGameMappingRepository.findByExternalGameId(matchId).isPresent();
    }

    /**
     * 기존 경기를 업데이트
     */
    private void updateExistingGame(MatchResultResponse.Match match, Score score)
    {
        ExternalGameMapping externalGameMapping = externalGameMappingRepository.findByExternalGameId((long) match.id()).orElseThrow();
        LeagueGame leagueGame = leagueGameRepository.findById(externalGameMapping.getGameId()).orElseThrow();
        log.info("{leagueGame = }" +leagueGame +"{match status}"+ match.status());
        leagueGame.setLeagueGameStatus(updateLeagueGameStatus(match.status()));

        leagueGame.setScore(score);

        leagueGameRepository.save(leagueGame);

    }

    /**
     * 새 경기를 저장
     */
    private void saveNewGame(MatchResultResponse.Match match, LocalDateTime gameDate, Score score, LeagueTeam awayTeam, LeagueTeam homeTeam)
    {
        LeagueGame leagueGame = LeagueGame.builder()
                .leagueGameStatus(match.status().equals("FINISHED") ? LeagueGameStatus.END : LeagueGameStatus.BEFORE)
                .away(awayTeam)
                .season(awayTeam.getSeason())
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

    /**
     * 경기 점수 생성
     */
    private Score buildScore(MatchResultResponse.Match match)
    {
        return Score.builder()
                .awayScore(match.score().fullTime().away())
                .homeScore(match.score().fullTime().home())
                .build();
    }

    /**
     * UTC 날짜를 시스템 시간대로 변환.
     *
     * @param instant UTC 날짜
     * @return 변환된 LocalDateTime
     */
    private LocalDateTime convertToGameDateTime(Instant instant)
    {
        return instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    private LeagueGameStatus updateLeagueGameStatus(String status)
    {
        switch (status){
            case "GAMING":
                return LeagueGameStatus.GAMING;
            case "CANCELED":
                return LeagueGameStatus.CANCELED;
            case "BEFORE":
                return LeagueGameStatus.BEFORE;
            default:
                return LeagueGameStatus.END;
        }
    }

    /**
     * 외부 API 호출 간 제한을 위해 3초 동안 대기.
     */
    private void sleepForApiRateLimit()
    {
        try {
            Thread.sleep(SLEEP_TIME_IN_MILLIS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // 인터럽트 상태 복원
            log.error("Thread was interrupted during sleep", e);
        }
    }
}

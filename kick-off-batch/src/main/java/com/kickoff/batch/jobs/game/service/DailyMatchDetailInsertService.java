package com.kickoff.batch.jobs.game.service;

import com.kickoff.batch.config.feign.SoccerApiFeign;
import com.kickoff.batch.config.feign.api.temp.Match;
import com.kickoff.batch.config.feign.api.temp.Team;
import com.kickoff.core.soccer.game.*;
import com.kickoff.core.soccer.league.*;
import com.kickoff.core.soccer.player.Player;
import com.kickoff.core.soccer.player.PlayerRepository;
import com.kickoff.core.soccer.team.Goal;
import com.kickoff.core.soccer.team.GoalType;
import com.kickoff.core.soccer.team.Score;
import com.kickoff.core.soccer.team.external.ExternalPlayerIdMapping;
import com.kickoff.core.soccer.team.external.ExternalPlayerIdMappingRepository;
import com.kickoff.core.soccer.team.external.ExternalTeamIdMappingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Transactional
@RequiredArgsConstructor
@Service
@Slf4j
public class DailyMatchDetailInsertService {
    private final SoccerApiFeign soccerApiFeign;
    private final SeasonRepository seasonRepository;
    private final LeagueTeamRepository leagueTeamRepository;
    private final PlayerRepository playerRepository;
    private final LeagueGameRepository leagueGameRepository;
    private final ExternalTeamIdMappingRepository externalTeamIdMappingRepository;
    private final ExternalPlayerIdMappingRepository  externalPlayerIdMappingRepository;
    private final ExternalGameMappingRepository externalGameMappingRepository;
    private final LeagueRepository leagueRepository;

    public void insertMatchDetail(LocalDate targetDateFrom, LocalDate targetDateTo, String season)
    {
        List<LeagueGame> targetLeagueGames = leagueGameRepository.findByGameDateBetween(targetDateFrom.atStartOfDay(), targetDateTo.atTime(23, 59, 59))
                .stream()
                .filter(game -> game.getLeagueGameStatus().equals(LeagueGameStatus.END))
                .toList();

        Season findSeason = seasonRepository.findByYears(season).orElseThrow();

        for (LeagueGame leagueGame : targetLeagueGames)
        {
            externalGameMappingRepository.findByGameId(leagueGame.getLeagueGameId()).ifPresent(externalGameMapping -> processMatchDetails(externalGameMapping, leagueGame, findSeason));
            sleepForApiRateLimiting();
        }
    }

    private void processMatchDetails(ExternalGameMapping externalGameId, LeagueGame leagueGame, Season season)
    {
        Match match = soccerApiFeign.getCompetitionMatchResponse(externalGameId.getExternalGameId());
        saveMatchDetails(match, leagueGame, season);
    }

    private void saveMatchDetails(Match match, LeagueGame leagueGame, Season season)
    {
        leagueGame.setMinute(match.minute());
        leagueGame.setInjuryTime(match.injuryTime());
        settingScore(match, leagueGame);
        settingGoals(match, leagueGame, season);
        settingLineUps(match, leagueGame);

        leagueGameRepository.save(leagueGame);
    }

    private void settingLineUps(Match match, LeagueGame leagueGame)
    {
        leagueGame.getGameLineUps().clear();
        settingLineUps(match.homeTeam(), "home", leagueGame);
        settingLineUps(match.awayTeam(), "away", leagueGame);
    }

    private void settingLineUps(Team match, String home, LeagueGame leagueGame)
    {
        match.lineup().forEach(lineUp ->
        {
            Optional<ExternalPlayerIdMapping> externalPlayer = externalPlayerIdMappingRepository.findByExternalPlayerId(Long.parseLong(lineUp.id()));

            if (externalPlayer.isPresent())
            {
                log.info("byExternalPlayerId is empty");
                ExternalPlayerIdMapping externalPlayerIdMapping = externalPlayer.get();
                Player player = playerRepository.findByPlayerId(externalPlayerIdMapping.getPlayerId()).orElseThrow();

                GameLineUp gameLineUp = new GameLineUp(externalPlayerIdMapping.getPlayerId(),
                        lineUp.position(),
                        lineUp.shirtNumber(),
                        home,
                        player.getPlayerKrName(),
                        player.getPlayerName());

                leagueGame.addGameLineUp(gameLineUp);
            }
        });
    }

    private static void settingScore(Match match, LeagueGame leagueGame)
    {
        Score score = Score.builder().awayScore(match.score().fullTime().away()).homeScore(match.score().fullTime().home()).build();
        leagueGame.setScore(score);
    }

    private void settingGoals(Match match, LeagueGame leagueGame, Season season)
    {
        if (!leagueGame.getGoals().isEmpty())
        {
            log.info("exists goals info : {}", leagueGame.getLeagueGameId());
            return;
        }

        List<Goal> goals = match.goals().stream()
                .map(matchGoal ->
                        externalTeamIdMappingRepository.findByExternalTeamIdAndSeason((long) matchGoal.team().id(), season.getYears()).map(externalTeamIdMapping ->
                        {
                            LeagueTeam scoredTeam = leagueTeamRepository.findById(externalTeamIdMapping.getTeamId())
                                    .orElseThrow(() -> new IllegalArgumentException("Team not found: " + externalTeamIdMapping.getTeamId()));

                            Optional<ExternalPlayerIdMapping> byExternalPlayerId = externalPlayerIdMappingRepository.findByExternalPlayerId(matchGoal.scorer().id());

                            if (byExternalPlayerId.isEmpty())
                            {
                                log.info("byExternalPlayerId is empty");
                                return null;
                            }

                            ExternalPlayerIdMapping externalPlayerIdMapping = byExternalPlayerId.get();
                            Player player = playerRepository.findById(externalPlayerIdMapping.getPlayerId())
                                    .orElseThrow(() -> new IllegalArgumentException("Player not found: " + matchGoal.scorer().id()));

                            GoalType goalType = GoalType.valueOf(matchGoal.type().toUpperCase());

                            return Goal.builder()
                                    .player(player)
                                    .scoredTeam(scoredTeam)
                                    .playTime(matchGoal.minute())
                                    .type(goalType)
                                    .build();
                        })
                        .orElseGet(() ->
                        {
                            log.warn("No ExternalTeamIdMapping found for TeamId: {}", matchGoal.team().id());
                            return null;
                        })
                )
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        leagueGame.setGoals(goals);
    }

    private void sleepForApiRateLimiting()
    {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread interrupted during sleep", e);
        }
    }
}

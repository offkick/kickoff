package com.kickoff.batch.jobs.game.service;

import com.kickoff.batch.config.feign.SoccerApiFeign;
import com.kickoff.batch.config.feign.api.temp.Match;
import com.kickoff.core.soccer.player.Player;
import com.kickoff.core.soccer.player.PlayerRepository;
import com.kickoff.core.soccer.team.Goal;
import com.kickoff.core.soccer.team.GoalType;
import com.kickoff.core.soccer.team.Score;
import com.kickoff.core.soccer.team.external.ExternalPlayerIdMapping;
import com.kickoff.core.soccer.team.external.ExternalPlayerIdMappingRepository;
import com.kickoff.core.soccer.team.external.ExternalTeamIdMappingRepository;
import com.kickoff.core.soccer.team.league.*;
import com.kickoff.core.soccer.team.league.game.ExternalGameMappingRepository;
import com.kickoff.core.soccer.team.league.game.LeagueGame;
import com.kickoff.core.soccer.team.league.game.LeagueGameRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public void insertMatchDetail(String season, String competition)
    {
        Season findSeason = seasonRepository.findByYears(season).orElseThrow();
        League league = leagueRepository.findByLeagueNameAndSeason(competition, findSeason).orElseThrow();

        List<LeagueGame> leagueGames = leagueGameRepository.findBySeason(findSeason);

        for (LeagueGame leagueGame : leagueGames)
        {
            if (!checkLeagueId(league, leagueGame))
            {
                continue;
            }

            externalGameMappingRepository.findByGameId(leagueGame.getLeagueGameId()).ifPresent(externalGameMapping -> processMatchDetails(externalGameMapping.getExternalGameId(), leagueGame, findSeason));
            sleepForApiRateLimiting();
        }
    }

    private static boolean checkLeagueId(League league, LeagueGame leagueGame) {
        return leagueGame.getAway().getLeague().getLeagueId().equals(league.getLeagueId()) && leagueGame.getHome().getLeague().getLeagueId().equals(league.getLeagueId());
    }

    private void processMatchDetails(Long externalGameId, LeagueGame leagueGame, Season season)
    {
        Match match = soccerApiFeign.getCompetitionMatchResponse(externalGameId);
        externalGameMappingRepository.findByExternalGameId(match.id().longValue())
                .ifPresentOrElse(
                        mapping -> saveMatchDetails(match, leagueGame, season),
                        () -> log.warn("No game information found for ExternalGameId: {}", match.id())
                );
    }

    private void saveMatchDetails(Match match, LeagueGame leagueGame, Season season)
    {
        Score score = Score.builder().awayScore(match.score().fullTime().away()).homeScore(match.score().fullTime().home()).build();
        List<Goal> goals = match.goals().stream()
                .map(matchGoal -> externalTeamIdMappingRepository.findByExternalTeamIdAndSeason((long) matchGoal.team().id(), season.getYears())
                        .map(externalTeamIdMapping -> {
                            LeagueTeam scoredTeam = leagueTeamRepository.findById(externalTeamIdMapping.getTeamId())
                                    .orElseThrow(() -> new IllegalArgumentException("Team not found: " + externalTeamIdMapping.getTeamId()));

                            Optional<ExternalPlayerIdMapping> byExternalPlayerId = externalPlayerIdMappingRepository.findByExternalPlayerId(matchGoal.scorer().id());

                            if (byExternalPlayerId.isEmpty()) {
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
                        .orElseGet(() -> {
                            log.warn("No ExternalTeamIdMapping found for TeamId: {}", matchGoal.team().id());
                            return null;
                        })
                )
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        leagueGame.setGoals(goals);
        leagueGame.setScore(score);

        leagueGameRepository.save(leagueGame);
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

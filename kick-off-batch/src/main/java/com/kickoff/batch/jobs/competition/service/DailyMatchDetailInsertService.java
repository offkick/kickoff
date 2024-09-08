package com.kickoff.batch.jobs.competition.service;

import com.kickoff.batch.config.feign.SoccerApiFeign;
import com.kickoff.batch.config.feign.api.temp.Match;
import com.kickoff.core.soccer.player.Player;
import com.kickoff.core.soccer.player.PlayerRepository;
import com.kickoff.core.soccer.team.Goal;
import com.kickoff.core.soccer.team.GoalType;
import com.kickoff.core.soccer.team.Score;
import com.kickoff.core.soccer.team.external.ExternalTeamIdMappingRepository;
import com.kickoff.core.soccer.team.league.LeagueTeam;
import com.kickoff.core.soccer.team.league.LeagueTeamRepository;
import com.kickoff.core.soccer.team.league.Season;
import com.kickoff.core.soccer.team.league.SeasonRepository;
import com.kickoff.core.soccer.team.league.game.ExternalGameMappingRepository;
import com.kickoff.core.soccer.team.league.game.LeagueGame;
import com.kickoff.core.soccer.team.league.game.LeagueGameRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
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
    private final ExternalGameMappingRepository externalGameMappingRepository;

    public void insertMatchDetail(String years)
    {
        Season season = seasonRepository.findByYears(years).orElseThrow();
        List<LeagueGame> leagueGames = leagueGameRepository.findBySeason(season);

        for (LeagueGame leagueGame : leagueGames)
        {
            externalGameMappingRepository.findByGameId(leagueGame.getLeagueGameId())
                    .ifPresent(externalGameMapping -> processMatchDetails(externalGameMapping.getExternalGameId(), leagueGame));
            sleepForApiRateLimiting();
        }
    }

    private void processMatchDetails(Long externalGameId, LeagueGame leagueGame)
    {
        Match match = soccerApiFeign.getCompetitionMatchResponse(externalGameId);
        externalGameMappingRepository.findByExternalGameId(match.id().longValue())
                .ifPresentOrElse(mapping -> saveMatchDetails(match, leagueGame), () -> log.warn("No game information found for ExternalGameId: {}", match.id()));
    }

    private void saveMatchDetails(Match match, LeagueGame leagueGame)
    {
        Score score = Score.builder().awayScore(match.score().fullTime().away()).homeScore(match.score().fullTime().home()).build();
        List<Goal> goals = match.goals().stream()
                .map(matchGoal -> externalTeamIdMappingRepository.findByExternalTeamId((long) matchGoal.team().id())
                        .map(externalTeamIdMapping -> {
                            LeagueTeam scoredTeam = leagueTeamRepository.findById(externalTeamIdMapping.getTeamId())
                                    .orElseThrow(() -> new IllegalArgumentException("Team not found: " + externalTeamIdMapping.getTeamId()));

                            Player player = playerRepository.findByPlayerId(matchGoal.scorer().id())
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

package com.kickoff.batch.jobs.competition.service;

import com.kickoff.batch.config.feign.SoccerApiFeign;
import com.kickoff.batch.config.feign.api.temp.Goals;
import com.kickoff.batch.config.feign.api.temp.Match;
import com.kickoff.core.soccer.player.Player;
import com.kickoff.core.soccer.player.PlayerRepository;
import com.kickoff.core.soccer.team.Goal;
import com.kickoff.core.soccer.team.GoalType;
import com.kickoff.core.soccer.team.Score;
import com.kickoff.core.soccer.team.external.ExternalTeamIdMapping;
import com.kickoff.core.soccer.team.external.ExternalTeamIdMappingRepository;
import com.kickoff.core.soccer.team.league.*;
import com.kickoff.core.soccer.team.league.game.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Transactional
@RequiredArgsConstructor
@Service
@Slf4j
public class DailyMatchScoreService {
    private final SoccerApiFeign soccerApiFeign;
    private final SeasonRepository seasonRepository;
    private final LeagueTeamRepository leagueTeamRepository;
    private final PlayerRepository playerRepository;
    private final LeagueGameRepository leagueGameRepository;
    private final ExternalTeamIdMappingRepository externalTeamIdMappingRepository;
    private final ExternalGameMappingRepository externalGameMappingRepository;

    public void insertMatchScore(LocalDateTime dateTime)
    {
        Season season = seasonRepository.findByYears("2023").orElse(Season.builder().years("2023").build());
        seasonRepository.save(season);

        List<LeagueGame> leagueGame = leagueGameRepository.findByGameDateBetween(dateTime.withHour(0).withMinute(0).withSecond(0), dateTime.withHour(23).withMinute(59).withSecond(59));
        log.info("leagueGame ={}" ,leagueGame);

        for(LeagueGame leagueGame1 : leagueGame){
            Optional<ExternalGameMapping> gameId = externalGameMappingRepository.findByGameId(leagueGame1.getLeagueGameId());
            if(leagueGame1.getLeagueGameId() == 7){
                log.info("result = {}" , gameId.get() );
            }

            if(gameId.isPresent() && leagueGame1.getLeagueGameId() == 7){

                ExternalGameMapping externalGameMapping = gameId.get();
                Long externalGameId = externalGameMapping.getExternalGameId();
                findMatches(externalGameId, leagueGame1);
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public void findMatches(Long matchId ,LeagueGame leagueGame){
        Match match = soccerApiFeign.getCompetitionMatchResponse(matchId);
        log.info("match = {}" , match);
        Optional<ExternalGameMapping> byExternalGameId = externalGameMappingRepository.findByExternalGameId((long) match.id());
        if(byExternalGameId.isEmpty())
        {
            log.info("게임 정보가 없는 게임id입니다.");
        }

        Score score = Score.builder()
                .awayScore(match.score().fullTime().away())
                .homeScore(match.score().fullTime().home())
                .build();

        List<Goal> goalsList = new ArrayList<>();

        for (Goals matchGoal : match.goals()) {
            Optional<ExternalTeamIdMapping> byExternalTeamId = externalTeamIdMappingRepository.findByExternalTeamId((long) matchGoal.team().id());
            ExternalTeamIdMapping externalTeamIdMapping2 = byExternalTeamId.get();
            LeagueTeam scoredTeam = leagueTeamRepository.findById(externalTeamIdMapping2.getTeamId()).orElseThrow();

            Long id = matchGoal.scorer().id();
            Player player = playerRepository.findByPlayerId(id).orElseThrow(IllegalArgumentException::new);
            GoalType goalType = GoalType.valueOf(matchGoal.type().toUpperCase());
            Goal goal = Goal.builder()
                    .player(player)
                    .scoredTeam(scoredTeam)
                    .playTime(matchGoal.minute())
                    .type(goalType)
                    .build();
            goalsList.add(goal);
        }

        leagueGame.setScore(score);
        leagueGame.setGoals(goalsList);
        leagueGameRepository.save(leagueGame);

    }
}

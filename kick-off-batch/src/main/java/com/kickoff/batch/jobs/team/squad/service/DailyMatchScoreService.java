package com.kickoff.batch.jobs.competition.service;

import com.kickoff.batch.config.feign.SoccerApiFeign;
import com.kickoff.batch.config.feign.api.MatchesResultDetailResponse;
import com.kickoff.core.soccer.player.Player;
import com.kickoff.core.soccer.player.PlayerRepository;
import com.kickoff.core.soccer.team.Goal;
import com.kickoff.core.soccer.team.GoalType;
import com.kickoff.core.soccer.team.Score;
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
import java.util.ArrayList;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
@Slf4j
public class DailyMatchScoreService {
    private static final Integer DIFF_DAYS = 10;
    private final SoccerApiFeign soccerApiFeign;
    private final LeagueRepository leagueRepository;
    private final SeasonRepository seasonRepository;
    private final LeagueTeamRepository leagueTeamRepository;
    private final PlayerRepository playerRepository;
    private final LeagueGameRepository leagueGameRepository;
    private final ExternalGameMappingRepository externalGameMappingRepository;

    public void insertMatchScore(LocalDate targetDateFrom, LocalDate targetDateTo, Long competitionId)
    {
        LocalDate currentDateTimeFrom = targetDateFrom;
        Season season = seasonRepository.findByYears("2023").orElse(Season.builder().years("2023").build());
        seasonRepository.save(season);
        while (currentDateTimeFrom.isBefore(targetDateTo)
        ) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            MatchesResultDetailResponse matchesResultDetailResponse = soccerApiFeign.getCompetitionMatchResponse(competitionId, currentDateTimeFrom.format(formatter), currentDateTimeFrom.plusDays(DIFF_DAYS).format(formatter));
            log.info("match" ,matchesResultDetailResponse);
            for(MatchesResultDetailResponse.Match match : matchesResultDetailResponse.matches())
            {
                String awayTeamName = match.awayTeam().name();
                String homeTeamName = match.homeTeam().name();
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

                Instant instant = match.utcDate().toInstant();
                LocalDateTime gameDate = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();

                // Goals 리스트 생성
                List<Goal> goalsList = new ArrayList<>();

                for (MatchesResultDetailResponse.Goals matchGoal : match.goals()) {
                    String scoredName = matchGoal.scoreTeam().name();

                    LeagueTeam scoredTeam = leagueTeamRepository.findByLeagueTeamName(scoredName)
                            .orElse(LeagueTeam.builder().leagueTeamName(scoredName).build());

                    Long id = matchGoal.scorer().id();
                    Player player = playerRepository.findById(id).orElseThrow(IllegalArgumentException::new);
                    GoalType goalType = GoalType.valueOf(matchGoal.type().toUpperCase());
                    Goal goal = Goal.builder()
                            .player(player)
                            .scoredTeam(scoredTeam)
                            .minute(matchGoal.minute())
                            .type(goalType)
                            .build();

                    // Goal 객체를 리스트에 추가
                    goalsList.add(goal);
                }

                LeagueGame leagueGame = LeagueGame.builder()
                        .leagueGameStatus(LeagueGameStatus.END)
                        .away(awayTeam)
                        .season(season)
                        .home(homeTeam)
                        .gameDate(gameDate)
                        .score(score)
                        .goals(goalsList)
                        .build();
                leagueGameRepository.save(leagueGame);

                externalGameMappingRepository.save(ExternalGameMapping.builder().externalGameId((long) match.id()).gameId(leagueGame.getLeagueGameId()).build());
            }
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

package com.kickoff.batch.jobs.league;

import com.kickoff.batch.config.UniqueRunIdIncrementer;
import com.kickoff.batch.config.feign.SoccerApiFeign;
import com.kickoff.batch.config.feign.api.LeaguePlayerRankResponse;
import com.kickoff.batch.jobs.team.standing.service.StandingBatchService;
import com.kickoff.core.soccer.league.LeagueTeam;
import com.kickoff.core.soccer.league.LeagueTeamRepository;
import com.kickoff.core.soccer.player.LeaguePlayerRank;
import com.kickoff.core.soccer.player.LeaguePlayerRankRepository;
import com.kickoff.core.soccer.player.Player;
import com.kickoff.core.soccer.player.PlayerRepository;
import com.kickoff.core.soccer.team.external.ExternalPlayerIdMapping;
import com.kickoff.core.soccer.team.external.ExternalPlayerIdMappingRepository;
import com.kickoff.core.soccer.team.external.ExternalTeamIdMapping;
import com.kickoff.core.soccer.team.external.ExternalTeamIdMappingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@RequiredArgsConstructor
@Configuration
public class PlayerStandingConfig {
    private final PlatformTransactionManager platformTransactionManager;
    private final StandingBatchService standingBatchService;
    private final JobExecutionListener jobCompletionEndListener;
    private final SoccerApiFeign soccerApiFeign;
    private final LeaguePlayerRankRepository leaguePlayerRankRepository;
    private final PlayerRepository playerRepository;
    private final LeagueTeamRepository leagueTeamRepository;
    private final ExternalPlayerIdMappingRepository externalPlayerIdMappingRepository;
    private final ExternalTeamIdMappingRepository externalTeamIdMappingRepository;
    @Bean
    public Job playerStandingJob(JobRepository jobRepository)
    {
        return new JobBuilder("playerStandingJob", jobRepository)
                .preventRestart()
                .incrementer(new UniqueRunIdIncrementer())
                .start(playerStaningStep(jobRepository))
                .build();
    }

    @Bean
    public Step playerStaningStep(JobRepository jobRepository)
    {
        return new StepBuilder("playerStaningStep", jobRepository)
                .tasklet(playerStaningTasklet(), platformTransactionManager)
                .build();
    }

    @Bean
    public Tasklet playerStaningTasklet()
    {
        return (contribution, chunkContext) -> {
            LeaguePlayerRankResponse leaguePlayerRankResponses = soccerApiFeign.leagueRank("PL", "2024" );
            for(LeaguePlayerRankResponse.LeaguePlayerRankDTO leaguePlayerRankResponse : leaguePlayerRankResponses.scorers())
            {

                ExternalPlayerIdMapping externalPlayerIdMapping = externalPlayerIdMappingRepository.findByExternalPlayerId(leaguePlayerRankResponse.player().getId()).orElseThrow();
                Player player = playerRepository.findByPlayerId(externalPlayerIdMapping.getPlayerId()).orElseThrow();

                ExternalTeamIdMapping externalTeamIdMapping = externalTeamIdMappingRepository.findByExternalTeamId(leaguePlayerRankResponse.team().id()).orElseThrow();
                LeagueTeam leagueTeam = leagueTeamRepository.findById(externalTeamIdMapping.getTeamId()).orElseThrow();

                if (!leaguePlayerRankRepository.existsByMatchDayAndPlayerId(leaguePlayerRankResponse.playedMatches(), player.getPlayerId()))
                {
                    LeaguePlayerRank leaguePlayerRank = LeaguePlayerRank.builder()
                            .playerId(player.getPlayerId())
                            .leagueTeamId(leagueTeam.getLeagueTeamId())
                            .assists(leaguePlayerRankResponse.assists())
                            .goals(leaguePlayerRankResponse.goals())
                            .penaltie(leaguePlayerRankResponse.penalties())
                            .matchDay(leaguePlayerRankResponse.playedMatches())
                            .section(leaguePlayerRankResponse.player().getSection())
                            .build();
                    leaguePlayerRankRepository.save(leaguePlayerRank);
                }
            }
            return RepeatStatus.FINISHED;
        };
    }
}

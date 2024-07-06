package com.kickoff.batch.jobs.competition;

import com.kickoff.batch.jobs.competition.service.DailyTeamSquadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.batch.core.scope.context.StepSynchronizationManager;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.concurrent.Executor;

/**
 * 리그 or 대회 팀 + 선수들을 업데이트한다.
 */
@Configuration
@RequiredArgsConstructor
@Slf4j
public class TeamSquadInsertJobConfig {
    private final PlatformTransactionManager platformTransactionManager;
    private final DailyTeamSquadService dailyTeamSquadService;

    @Bean
    public Job teamSquadInsertJob(JobRepository jobRepository)
    {
        return new JobBuilder("teamSquadInsertJob", jobRepository)
                .preventRestart()
                .incrementer(new RunIdIncrementer())
                .start(teamSquadInsertStep(jobRepository))
                .build();
    }

    @Bean
    public Executor taskExecutor()
    {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("async-");
        executor.initialize();
        return executor;
    }

    @Bean
    public Step teamSquadInsertStep(JobRepository jobRepository)
    {
        return new StepBuilder("teamSquadInsertStep", jobRepository)
                .tasklet(dailyImportSoccerTasklet(), platformTransactionManager)
                .build();
    }

    @Bean
    public Tasklet dailyImportSoccerTasklet()
    {
        return (contribution, chunkContext) ->
        {
            log.info("[Start teamSquadInsertJob]");
            StepContext stepContext = StepSynchronizationManager.getContext();
            if (stepContext != null)
            {
                JobParameters jobParameters = stepContext.getStepExecution().getJobParameters();
                String competition = jobParameters.getString("competition");
                String year = jobParameters.getString("year");
                log.info("[PARAMETER] year : {}, competition : {}", year, competition);
                dailyTeamSquadService.insertTeamSquad(year, competition);
            }
            log.info("[End DailyImportSoccerTasklet]");
            return RepeatStatus.FINISHED;
        };
    }
}
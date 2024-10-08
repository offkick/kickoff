package com.kickoff.batch.jobs.team.standing;

import com.kickoff.batch.config.UniqueRunIdIncrementer;
import com.kickoff.batch.jobs.team.standing.service.StandingBatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.batch.core.scope.context.StepSynchronizationManager;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * 리그 팀 순위를 가져온다
 */
@Slf4j
@RequiredArgsConstructor
@Configuration
public class StandingJogConfig {
    private final PlatformTransactionManager platformTransactionManager;
    private final StandingBatchService standingBatchService;
    private final JobExecutionListener jobCompletionEndListener;

    @Bean
    public Job dailyStandingInfo(JobRepository jobRepository)
    {
        return new JobBuilder("dailyStandingInfoJob", jobRepository)
                .preventRestart()
                .listener(jobCompletionEndListener)
                .incrementer(new UniqueRunIdIncrementer())
                .start(dailyStandingInfoStep(jobRepository))
                .build();
    }

    @Bean
    public Step dailyStandingInfoStep(JobRepository jobRepository)
    {
        return new StepBuilder("dailyStandingInfoStep", jobRepository)
                .tasklet(dailyStandingInfoTasklet(), platformTransactionManager)
                .build();
    }

    @Bean
    public Tasklet dailyStandingInfoTasklet() {
        return (contribution, chunkContext) ->
        {
            StepContext stepContext = StepSynchronizationManager.getContext();
            log.info("start dailyStandingInfoTasklet");
            JobParameters jobParameters = stepContext.getStepExecution().getJobParameters();

            String competitions = jobParameters.getString( "competitions");
            String season = jobParameters.getString( "season");

            standingBatchService.insertStanding(season, competitions);
            return RepeatStatus.FINISHED;
        };
    }
}

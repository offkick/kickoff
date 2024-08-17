package com.kickoff.batch.jobs.team.standing;

import com.kickoff.batch.jobs.team.standing.service.StandingBatchService;
import com.kickoff.batch.util.JobParameterUtil;
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

    @Bean
    public Job dailyStandingInfo(JobRepository jobRepository)
    {
        return new JobBuilder("dailyStandingInfoJob", jobRepository)
                .preventRestart()
                .incrementer(new RunIdIncrementer())
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
            String season = JobParameterUtil.getIfPresentStringParameter(jobParameters, "season");
            Long matchday = Long.valueOf(JobParameterUtil.getIfPresentStringParameter(jobParameters, "matchday"));
            Long leagueId = JobParameterUtil.getIfPresentLongParameter(jobParameters, "leagueId");

            for (int i=  1; i <= 38; i++) {
                Thread.sleep(4000L);
                standingBatchService.insertStanding(season, (long) i, leagueId);
            }
            return RepeatStatus.FINISHED;
        };
    }
}

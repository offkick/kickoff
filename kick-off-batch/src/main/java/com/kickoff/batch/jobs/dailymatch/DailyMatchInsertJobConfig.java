package com.kickoff.batch.jobs.dailymatch;

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

import java.time.LocalDate;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class DailyMatchInsertJobConfig {
    private final PlatformTransactionManager platformTransactionManager;
    private final MatchService matchService;
    @Bean
    public Job dailyMatchInsertJob(JobRepository jobRepository)
    {
        return new JobBuilder("dailyMatchInsertJob", jobRepository)
                .preventRestart()
                .incrementer(new RunIdIncrementer())
                .start(dailyMatchInsertStep(jobRepository))
                .build();
    }

    @Bean
    public Step dailyMatchInsertStep(JobRepository jobRepository)
    {
        return new StepBuilder("dailyImportSoccerScheduleStep", jobRepository)
                .tasklet(dailyMatchInsertTasklet(), platformTransactionManager)
                .build();
    }

    @Bean
    public Tasklet dailyMatchInsertTasklet()
    {
        return (contribution, chunkContext) ->
        {
            log.info("[Start dailyMatchInsertTasklet]");
            StepContext stepContext = StepSynchronizationManager.getContext();
            if (stepContext != null)
            {
                JobParameters jobParameters = stepContext.getStepExecution().getJobParameters();
                LocalDate targetDateFrom = jobParameters.getString("targetDateFrom") == null ? LocalDate.now() : LocalDate.parse(Objects.requireNonNull(jobParameters.getString("targetDateFrom")));
                LocalDate targetDateTo = jobParameters.getString("targetDateTo")  == null ? LocalDate.now() : LocalDate.parse(Objects.requireNonNull(jobParameters.getString("targetDateTo")));
                log.info("Input Parameter targetDateFrom : {}, targetDateTo : {}", targetDateFrom, targetDateTo);
                matchService.save(targetDateFrom, targetDateTo);
            }
            log.info("[End DailyImportSoccerTasklet]");
            return RepeatStatus.FINISHED;
        };
    }
}

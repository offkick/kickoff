package com.kickoff.batch.jobs.dailiysoccerschedule;

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

@Configuration
@RequiredArgsConstructor
@Slf4j
public class DailyImportSoccerScheduleJobConfig {

    private final PlatformTransactionManager platformTransactionManager;
    private final SoccerScheduleService soccerScheduleService;

    @Bean
    public Job dailyImportSoccerScheduleJob(JobRepository jobRepository)
    {
        return new JobBuilder("dailyImportSoccerScheduleJob", jobRepository)
                .preventRestart()
                .incrementer(new RunIdIncrementer())
                .start(dailyImportSoccerScheduleStep(jobRepository))
                .build();
    }

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("async-");
        executor.initialize();
        return executor;
    }

    @Bean
    public Step dailyImportSoccerScheduleStep(JobRepository jobRepository)
    {
        return new StepBuilder("dailyImportSoccerScheduleStep", jobRepository)
                .tasklet(dailyImportSoccerTasklet(), platformTransactionManager)
                .build();
    }

    @Bean
    public Tasklet dailyImportSoccerTasklet()
    {
        return (contribution, chunkContext) ->
        {
            StepContext stepContext = StepSynchronizationManager.getContext();
            if (stepContext != null)
            {
                JobParameters jobParameters = stepContext.getStepExecution().getJobParameters();
                String target = jobParameters.getString("target");
                log.info("[Start DailyImportSoccerTasklet]");
                soccerScheduleService.competitionInsert(target);
            }
            log.info("[End DailyImportSoccerTasklet]");
            return RepeatStatus.FINISHED;
        };
    }
}
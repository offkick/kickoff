package com.kickoff.batch.jobs.dailiysoccerschedule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class DailyImportSoccerScheduleJobConfig {

    private final PlatformTransactionManager platformTransactionManager;

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
    public Step dailyImportSoccerScheduleStep(JobRepository jobRepository)
    {
        return new StepBuilder("dailyImportSoccerScheduleStep", jobRepository)
                .tasklet(dailyImportSoccerTasklet(), platformTransactionManager)
                .build();
    }

    @Bean
    public Tasklet dailyImportSoccerTasklet()
    {
        return (contribution, chunkContext) -> {
            log.info("start dailyImportSoccerTasklet ...");
            return RepeatStatus.FINISHED;
        };
    }
}

package com.kickoff.batch.jobs.league;

import com.kickoff.batch.config.UniqueRunIdIncrementer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * 임시적으로 리그들만 업데이트 한다
 */
@Configuration
@RequiredArgsConstructor
@Slf4j
public class LeagueBatchInsertJobConfig {
    private final PlatformTransactionManager platformTransactionManager;

    @Bean
    public Job leagueBatchInsertJob(JobRepository jobRepository)
    {
        return new JobBuilder("leagueBatchInsertJob", jobRepository)
                .preventRestart()
                .incrementer(new UniqueRunIdIncrementer())
                .start(leagueBatchInsertStep(jobRepository))
                .build();
    }

    @Bean
    public Step leagueBatchInsertStep(JobRepository jobRepository)
    {
        return new StepBuilder("teamSquadInsertStep", jobRepository)
                .tasklet(leagueBatchInsertStepTasklet(), platformTransactionManager)
                .build();
    }

    @Bean
    public Tasklet leagueBatchInsertStepTasklet()
    {
        return (contribution, chunkContext) -> {
            return RepeatStatus.FINISHED;
        };
    }

}

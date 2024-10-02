package com.kickoff.batch.jobs.game;

import com.kickoff.batch.config.UniqueRunIdIncrementer;
import com.kickoff.batch.jobs.game.service.CompetitionInsertService;
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


@Configuration
@RequiredArgsConstructor
@Slf4j
public class CompetitionInsertJobConfig {
    private final PlatformTransactionManager platformTransactionManager;
    private final CompetitionInsertService competitionInsertService;
    private final JobExecutionListener jobCompletionEndListener;

    @Bean
    public Job competitionInsertJob(JobRepository jobRepository)
    {
        return new JobBuilder("competitionInsertJob", jobRepository)
                .preventRestart()
                .listener(jobCompletionEndListener)
                .incrementer(new UniqueRunIdIncrementer())
                .start(competitionInsertJobStep(jobRepository))
                .build();
    }

    @Bean
    public Step competitionInsertJobStep(JobRepository jobRepository)
    {
        return new StepBuilder("competitionInsertJobStep", jobRepository)
                .tasklet(importCompetitionTasklet(), platformTransactionManager)
                .build();
    }

    @Bean
    public Tasklet importCompetitionTasklet()
    {
        return (contribution, chunkContext)->{
            StepContext stepContext = StepSynchronizationManager.getContext();
            if(stepContext!=null)
            {
                JobParameters jobParameters = stepContext.getStepExecution().getJobParameters();
                String competitions = jobParameters.getString("competitions");
                competitionInsertService.insertCompetition(competitions);
            }
            return RepeatStatus.FINISHED;
        };
    }
}

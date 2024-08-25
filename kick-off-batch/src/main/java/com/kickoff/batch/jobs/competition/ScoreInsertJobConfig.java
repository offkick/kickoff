package com.kickoff.batch.jobs.competition;

import com.kickoff.batch.jobs.competition.service.DailyMatchScoreService;
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

/**
 * 경기 결과 업데이트
 */
@Configuration
@RequiredArgsConstructor
@Slf4j
public class ScoreInsertJobConfig {
    private final PlatformTransactionManager platformTransactionManager;
    private final DailyMatchScoreService dailyMatchScoreService;

    @Bean
    public Job scoreInsertJob(JobRepository jobRepository)
    {
        return new JobBuilder("scoreInsertJob",jobRepository)
                .preventRestart()
                .incrementer(new RunIdIncrementer())
                .start(scoreInsertJobStep(jobRepository))
                .build();
    }

    @Bean
    public Step scoreInsertJobStep(JobRepository jobRepository)
    {
        return new StepBuilder("scoreInsertJobStep", jobRepository)
                .tasklet(dailyImportScoreTasklet(), platformTransactionManager)
                .build();
    }

    @Bean
    public Tasklet dailyImportScoreTasklet()
    {
        return (contribution, chunkContext)->{
            StepContext stepContext = StepSynchronizationManager.getContext();
            if(stepContext!=null)
            {
                JobParameters jobParameters = stepContext.getStepExecution().getJobParameters();
                LocalDate targetDateFrom = jobParameters.getString("targetDateFrom") == null ? LocalDate.now() : LocalDate.parse(Objects.requireNonNull(jobParameters.getString("targetDateFrom")));
                LocalDate targetDateTo = jobParameters.getString("targetDateTo")  == null ? LocalDate.now() : LocalDate.parse(Objects.requireNonNull(jobParameters.getString("targetDateTo")));
                String competitions = jobParameters.getString("competitions")  == null ? "2021" : jobParameters.getString("competitions");
                Long competitionId = Long.parseLong(competitions);

                dailyMatchScoreService.insertMatchScore(targetDateFrom,targetDateTo,competitionId);

            }
            return RepeatStatus.FINISHED;
        };
    }
}

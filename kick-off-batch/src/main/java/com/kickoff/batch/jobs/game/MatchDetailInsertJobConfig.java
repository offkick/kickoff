package com.kickoff.batch.jobs.game;

import com.kickoff.batch.config.UniqueRunIdIncrementer;
import com.kickoff.batch.jobs.game.service.DailyMatchDetailInsertService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
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

import java.time.LocalDate;
import java.util.Objects;

/**
 * 경기 상세 결과 업데이트
 */
@Configuration
@RequiredArgsConstructor
@Slf4j
public class MatchDetailInsertJobConfig {
    private final PlatformTransactionManager platformTransactionManager;
    private final DailyMatchDetailInsertService dailyMatchDetailInsertService;
    private final JobExecutionListener jobCompletionEndListener;

    @Bean
    public Job matchDetailInsertJob(JobRepository jobRepository)
    {
        return new JobBuilder("matchDetailInsertJob",jobRepository)
                .preventRestart()
                .listener(jobCompletionEndListener)
                .incrementer(new UniqueRunIdIncrementer())
                .start(matchDetailInsertJobStep(jobRepository))
                .build();
    }

    @Bean
    public Step matchDetailInsertJobStep(JobRepository jobRepository)
    {
        return new StepBuilder("matchDetailInsertJobStep", jobRepository)
                .tasklet(matchDetailInsertTasklet(), platformTransactionManager)
                .build();
    }

    @Bean
    @StepScope
    public Tasklet matchDetailInsertTasklet()
    {
        return (contribution, chunkContext)->{
            StepContext stepContext = StepSynchronizationManager.getContext();
            JobParameters jobParameters = stepContext.getStepExecution().getJobParameters();

            LocalDate targetDateFrom = parseDateOrDefault(jobParameters.getString("targetDateFrom"), LocalDate.now().minusDays(3));
            LocalDate targetDateTo = parseDateOrDefault(jobParameters.getString("targetDateTo"), LocalDate.now().plusDays(1));
            String season = jobParameters.getString("season");
            log.info("start parameter with start [targetDateFrom] : {}, [targetDateTo] : {}", targetDateFrom, targetDateTo);
            dailyMatchDetailInsertService.insertMatchDetail(targetDateFrom, targetDateTo, season);
            return RepeatStatus.FINISHED;
        };
    }

    private LocalDate parseDateOrDefault(String date, LocalDate defaultDate)
    {
        return (date == null || date.isBlank())? defaultDate : LocalDate.parse(Objects.requireNonNull(date));
    }
}

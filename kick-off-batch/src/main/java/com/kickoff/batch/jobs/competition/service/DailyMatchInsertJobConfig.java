package com.kickoff.batch.jobs.competition.service;

import com.kickoff.batch.jobs.game.service.DailyMatchInsertService;
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
 * 일 단위로 경기 리스트를 조회해서 경기 + 팀 정보를 업데이트 한다.
 */
@Slf4j
@RequiredArgsConstructor
@Configuration
public class DailyMatchInsertJobConfig {

    private final PlatformTransactionManager platformTransactionManager;
    private final DailyMatchInsertService dailyMatchInsertService;

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
        return (contribution, chunkContext) -> {
            log.info("[Start dailyMatchInsertTasklet]");
            JobParameters jobParameters = getJobParameters();

            LocalDate targetDateFrom = parseDateOrDefault(jobParameters.getString("targetDateFrom"), LocalDate.now());
            LocalDate targetDateTo = parseDateOrDefault(jobParameters.getString("targetDateTo"), LocalDate.now());
            String competitions = jobParameters.getString("competitions") == null ? "PL" : jobParameters.getString("competitions");

            log.info("Input Parameter targetDateFrom: {}, targetDateTo: {}, competitions: {}", targetDateFrom, targetDateTo, competitions);
            dailyMatchInsertService.insertMatch(targetDateFrom, targetDateTo, competitions);
            log.info("[End dailyMatchInsertTasklet]");
            return RepeatStatus.FINISHED;
        };
    }

    private JobParameters getJobParameters()
    {
        StepContext stepContext = StepSynchronizationManager.getContext();
        if (stepContext == null)
        {
            log.error("[Error] StepContext is null, cannot retrieve job parameters.");
            throw new IllegalStateException("StepContext not available");
        }

        JobParameters jobParameters = stepContext.getStepExecution().getJobParameters();
        if (jobParameters == null)
        {
            log.error("[Error] JobParameters is null.");
            throw new IllegalStateException("JobParameters not found");
        }

        return jobParameters;
    }

    private LocalDate parseDateOrDefault(String date, LocalDate defaultDate)
    {
        return date == null ? defaultDate : LocalDate.parse(Objects.requireNonNull(date));
    }
}
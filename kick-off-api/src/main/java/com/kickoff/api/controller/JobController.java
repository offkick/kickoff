package com.kickoff.api.controller;

import com.kickoff.batch.jobs.competition.DailyMatchInsertJobConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
public class JobController {
    private final JobLauncher jobLauncher;
    private final DailyMatchInsertJobConfig dailyMatchInsertJobConfig;
    private final JobRepository jobRepository;

    @Scheduled(cron = "0 0 8 * * *")
    public void matchInsert() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("competitions", "PL")
                .addString("targetDateFrom", LocalDate.now().minusDays(1).toString())
                .addString("targetDateTo", LocalDate.now().minusDays(1).toString())
                .addString("seasonYear", "2024")
                .toJobParameters();
        jobLauncher.run(dailyMatchInsertJobConfig.dailyMatchInsertJob(jobRepository), jobParameters);
    }

    @Scheduled(cron = "0 30 8 * * *")
    public void matchInsertLa() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("competitions", "PD")
                .addString("targetDateFrom", LocalDate.now().minusDays(1).toString())
                .addString("targetDateTo", LocalDate.now().minusDays(1).toString())
                .addString("seasonYear", "2024")
                .toJobParameters();
        jobLauncher.run(dailyMatchInsertJobConfig.dailyMatchInsertJob(jobRepository), jobParameters);
    }
}

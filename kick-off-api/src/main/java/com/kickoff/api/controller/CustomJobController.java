package com.kickoff.api.controller;

import com.kickoff.batch.jobs.competition.DailyMatchInsertJobConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
public class CustomJobController {
    private final JobLauncher jobLauncher;
    private final JobRepository jobRepository;
    private final DailyMatchInsertJobConfig dailyMatchInsertJobConfig;

    @Scheduled(cron = "0 30 8 * * *")
    public void matchInsertLa() throws Exception
    {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("competitions", "PD,PL")
                .addString("targetDateFrom", LocalDate.now().minusDays(1).toString())
                .addString("targetDateTo", LocalDate.now().minusDays(1).toString())
                .addString("seasonYear", "2024")
                .toJobParameters();
        jobLauncher.run(dailyMatchInsertJobConfig.dailyMatchInsertJob(jobRepository), jobParameters);
    }
}

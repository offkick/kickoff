package com.kickoff.batch.jobs.team.squad;

import com.kickoff.batch.config.UniqueRunIdIncrementer;
import com.kickoff.batch.jobs.team.squad.service.DailyTeamSquadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 리그 or 대회 팀 + 선수들을 업데이트한다.
 */
@Configuration
@RequiredArgsConstructor
@Slf4j
public class TeamSquadInsertJobConfig {

    private final PlatformTransactionManager platformTransactionManager;
    private final DailyTeamSquadService dailyTeamSquadService;

    @Bean
    public Job teamSquadInsertJob(JobRepository jobRepository)
    {
        return new JobBuilder("teamSquadInsertJob", jobRepository)
                .preventRestart()
                .incrementer(new UniqueRunIdIncrementer())
                .start(teamSquadInsertStep(jobRepository))
                .build();
    }

    @Bean
    public Step teamSquadInsertStep(JobRepository jobRepository)
    {
        return new StepBuilder("teamSquadInsertStep", jobRepository)
                .tasklet(dailyImportSoccerTasklet(), platformTransactionManager)
                .build();
    }

    @Bean
    @StepScope
    public Tasklet dailyImportSoccerTasklet()
    {
        return (contribution, chunkContext) -> {
            log.info("[Start] teamSquadInsertJob");

            JobParameters jobParameters = getJobParameters();

            String competitions = jobParameters.getString("competitions");
            String season = jobParameters.getString("season");

            validateJobParameters(competitions, season);
            processTeamSquadInsert(Arrays.stream(competitions.split(",")).collect(Collectors.toList()), season);

            log.info("[End] DailyImportSoccerTasklet");
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

    private void validateJobParameters(String competition, String year)
    {
        if (competition == null || year == null)
        {
            log.error("[Error] Missing required job parameters: competition or year.");
            throw new IllegalArgumentException("Required job parameters are missing");
        }
        log.info("[PARAMETER] year: {}, competition: {}", year, competition);
    }

    private void processTeamSquadInsert(List<String> competitions, String year)
    {
        try
        {
            for (String competition: competitions)
            {
                log.info("competition insert : {}", competition);
                dailyTeamSquadService.insertTeamSquad(year, competition);
            }

        } catch (Exception e)
        {
            log.error("[Error] Failed to insert team squad data: {}", e.getMessage());
            throw e;
        }
    }
}
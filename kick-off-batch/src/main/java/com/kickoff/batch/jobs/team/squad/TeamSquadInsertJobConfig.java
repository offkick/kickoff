package com.kickoff.batch.jobs.team.squad;

import com.kickoff.batch.jobs.team.squad.service.DailyTeamSquadService;
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
                .incrementer(new RunIdIncrementer())
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
    public Tasklet dailyImportSoccerTasklet()
    {
        return (contribution, chunkContext) -> {
            log.info("[Start] teamSquadInsertJob");

            JobParameters jobParameters = getJobParameters();

            // 필요한 파라미터들을 가져와서 유효성 검사 및 서비스 호출
            String competition = jobParameters.getString("competition");
            String year = jobParameters.getString("year");

            validateJobParameters(competition, year);
            processTeamSquadInsert(competition, year);

            log.info("[End] DailyImportSoccerTasklet");
            return RepeatStatus.FINISHED;
        };
    }

    // JobParameters를 가져오는 메서드
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

    // 파라미터 유효성 검사 메서드
    private void validateJobParameters(String competition, String year)
    {
        if (competition == null || year == null)
        {
            log.error("[Error] Missing required job parameters: competition or year.");
            throw new IllegalArgumentException("Required job parameters are missing");
        }
        log.info("[PARAMETER] year: {}, competition: {}", year, competition);
    }

    // 서비스 호출 및 예외 처리 메서드
    private void processTeamSquadInsert(String year, String competition)
    {
        try
        {
            dailyTeamSquadService.insertTeamSquad("2024", "PL");
            log.info("[Success] Team squad data inserted successfully.");
        } catch (Exception e)
        {
            log.error("[Error] Failed to insert team squad data: {}", e.getMessage());
            throw e;
        }
    }
}
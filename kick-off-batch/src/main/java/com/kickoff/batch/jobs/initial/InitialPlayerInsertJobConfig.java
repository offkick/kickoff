package com.kickoff.batch.jobs.initial;

import com.kickoff.batch.jobs.competition.service.DailyTeamSquadService;
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


@Slf4j
@RequiredArgsConstructor
@Configuration
public class InitialPlayerInsertJobConfig {

    private final DailyTeamSquadService dailyTeamSquadService;
    private final PlatformTransactionManager platformTransactionManager;

    @Bean
    public Job competitionInsertJob(JobRepository jobRepository)
    {
        return new JobBuilder("competitionBulkInsertJob", jobRepository)
                .preventRestart()
                .incrementer(new RunIdIncrementer())
                .start(competitionInsertStep(jobRepository))
                .build();
    }

    @Bean
    public Step competitionInsertStep(JobRepository jobRepository)
    {
        return new StepBuilder("competitionInsertBulkStep", jobRepository)
                .tasklet(competitionInsertTasklet(), platformTransactionManager)
                .build();
    }

    @Bean
    public Tasklet competitionInsertTasklet()
    {
        return (contribution, chunkContext) -> {
            log.info("start competitionInsertJob");
            StepContext stepContext = StepSynchronizationManager.getContext();

            if (stepContext != null)
            {
                JobParameters jobParameters = stepContext.getStepExecution().getJobParameters();
                String year = jobParameters.getString("year");
                String competition = jobParameters.getString("competition");
                log.info("[PARAMETER] year : {}, competition : {}", year, competition);
                dailyTeamSquadService.insertTeamSquad(year, competition);
            }
            else {
                log.info("target parameter is null");
            }
            return RepeatStatus.FINISHED;
        };
    }
}

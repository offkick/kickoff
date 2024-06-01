package com.kickoff.batch.jobs.initial;

import com.kickoff.batch.jobs.dailiysoccerschedule.SoccerScheduleService;
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


@Slf4j
@RequiredArgsConstructor
@Configuration
public class InitialPlayerInsertJobConfig {

    private final SoccerScheduleService soccerScheduleService;
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
            StepContext stepContext = StepSynchronizationManager.getContext();
            String target = null;

            if (stepContext != null)
            {
                JobParameters jobParameters = stepContext.getStepExecution().getJobParameters();
                target = jobParameters.getString("target");
                log.info("start competitionInsertTasklet... {}", target);
                soccerScheduleService.competitionInsert(target);
            }
            else {
                log.info("target parameter is null");
            }
            return RepeatStatus.FINISHED;
        };
    }
}

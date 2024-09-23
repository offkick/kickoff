package com.kickoff.batch.jobs.data;

import com.kickoff.batch.config.UniqueRunIdIncrementer;
import com.kickoff.batch.jobs.data.service.AllDataDeleteService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@RequiredArgsConstructor
@EnableBatchProcessing
public class AllDataDeleteJobConfig {
    private final PlatformTransactionManager platformTransactionManager;
    private final AllDataDeleteService allDataDeleteService;

    @Bean
    public Job deleteAllDataJob(JobRepository jobRepository){
        return new JobBuilder("deleteAllDataJob",jobRepository)
                .preventRestart()
                .incrementer(new UniqueRunIdIncrementer())
                .start(deleteAllDataStep(jobRepository))
                .build();
    }

    @Bean
    public Step deleteAllDataStep(JobRepository jobRepository){
        return new StepBuilder("deleteAllDataTasklet", jobRepository)
                .tasklet(deleteAllDataTasklet(), platformTransactionManager)
                .build();
    }

    @Bean
    @Transactional
    public Tasklet deleteAllDataTasklet(){
        return (contribution, chunkContext) -> {
            allDataDeleteService.deleteAllData();
            return RepeatStatus.FINISHED;

        };
    }
}

package com.kickoff.batch.jobs.matchindex;

import com.kickoff.core.soccer.team.league.LeagueTeam;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@RequiredArgsConstructor
@Configuration
public class MatchIndexingJobConfig {

    private static final Integer CHUNK_SIZE = 5;

    private final PlatformTransactionManager platformTransactionManager;
    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    @Bean
    public Job matchIndexingJob(JobRepository jobRepository)
    {
        return new JobBuilder("matchIndexJob", jobRepository)
                .preventRestart()
                .incrementer(new RunIdIncrementer())
                .start(matchIndexingStep(jobRepository))
                .build();
    }

    @Bean
    public Step matchIndexingStep(JobRepository jobRepository)
    {
        return new StepBuilder("matchIndexStep", jobRepository)
                .<LeagueTeam, LeagueTeamDTO>chunk(CHUNK_SIZE, platformTransactionManager)
                .listener(new LeagueTeamListener())
                .listener(new LeagueTeamChunkListener())
                .reader(matchIndexItemReader(entityManagerFactory))
                .processor(itemProcessor())
                .writer(new ItemLeagueTeamWriter())
                .build();
    }

    @Bean
    public ItemProcessor<LeagueTeam, LeagueTeamDTO> itemProcessor()
    {
        return LeagueTeamDTO::of;
    }

    @Bean
    public JpaPagingItemReader<LeagueTeam> matchIndexItemReader(EntityManagerFactory entityManagerFactory)
    {
        MatchQueryProvider provider = new MatchQueryProvider(entityManager);
        return new JpaPagingItemReaderBuilder<LeagueTeam>()
                .name("matchIndexReader")
                .pageSize(CHUNK_SIZE)
                .entityManagerFactory(entityManagerFactory)
                .queryProvider(provider)
                .build();
    }
}

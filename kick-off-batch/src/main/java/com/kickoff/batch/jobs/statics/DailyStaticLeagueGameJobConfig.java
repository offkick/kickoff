package com.kickoff.batch.jobs.statics;

import com.kickoff.batch.jobs.matchindex.ItemLeagueGameWriter;
import com.kickoff.batch.jobs.matchindex.LeagueGameStaticProvider;
import com.kickoff.core.soccer.team.league.LeagueGameStatic;
import com.kickoff.core.soccer.team.league.game.LeagueGame;
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

/**
 * LeagueGame 테이블을 새로운 LeagueGameStatics 테이블로 옮긴다.
 */
@RequiredArgsConstructor
@Configuration
public class DailyStaticLeagueGameJobConfig {
    private static final Integer CHUNK_SIZE = 20;

    private final PlatformTransactionManager platformTransactionManager;
    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;
    private final ItemLeagueGameWriter itemLeagueGameWriter;

    @Bean
    public Job dailyStaticLeagueGameJob(JobRepository jobRepository)
    {
        return new JobBuilder("dailyStaticLeagueGameJob", jobRepository)
                .preventRestart()
                .incrementer(new RunIdIncrementer())
                .start(dailyStaticLeagueGameJobStep(jobRepository))
                .build();
    }

    @Bean
    public Step dailyStaticLeagueGameJobStep(JobRepository jobRepository)
    {
        return new StepBuilder("dailyStaticLeagueGameJobStep", jobRepository)
                .<LeagueGame, LeagueGameStatic>chunk(CHUNK_SIZE, platformTransactionManager)
                .reader(dailyStaticLeagueGameJobItemReader(entityManagerFactory))
                .processor(dailyStaticLeagueGameItemProcessor())
                .writer(itemLeagueGameWriter)
                .build();
    }

    @Bean
    public ItemProcessor<LeagueGame, LeagueGameStatic> dailyStaticLeagueGameItemProcessor()
    {
        return LeagueGameStatic::of;
    }

    @Bean
    public JpaPagingItemReader<LeagueGame> dailyStaticLeagueGameJobItemReader(EntityManagerFactory entityManagerFactory)
    {
        LeagueGameStaticProvider provider = new LeagueGameStaticProvider(entityManager);
        return new JpaPagingItemReaderBuilder<LeagueGame>()
                .name("dailyStaticLeagueGameJobItemReader")
                .queryProvider(provider)
                .pageSize(CHUNK_SIZE)
                .entityManagerFactory(entityManagerFactory)
                .queryProvider(provider)
                .build();
    }
}

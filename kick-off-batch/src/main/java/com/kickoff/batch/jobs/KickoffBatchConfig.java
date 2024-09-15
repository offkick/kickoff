package com.kickoff.batch.jobs;

import org.springframework.batch.core.JobExecutionListener;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KickoffBatchConfig {
    private final ApplicationContext context;

    public KickoffBatchConfig(ApplicationContext context)
    {
        this.context = context;
    }

    @Bean
    public JobExecutionListener jobCompletionEndListener()
    {
        return new JobCompletionEndListener(context);
    }
}

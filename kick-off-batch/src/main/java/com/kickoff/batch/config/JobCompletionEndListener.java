package com.kickoff.batch.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.context.ApplicationContext;

@Slf4j
public class JobCompletionEndListener implements JobExecutionListener {
    private final ApplicationContext context;

    public JobCompletionEndListener(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void beforeJob(JobExecution jobExecution)
    {

    }

    @Override
    public void afterJob(JobExecution jobExecution)
    {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            System.exit(0);
        }
    }
}

package com.kickoff.batch.jobs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

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
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED)
        {
            SpringApplication.exit(context, () -> 0);
        } else if (jobExecution.getStatus() == BatchStatus.FAILED)
        {
            System.out.println("Job failed Shutting down...");
            SpringApplication.exit(context, () -> 1);
        }
    }
}

package com.kickoff.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
public class CustomJobController {
    private final JobLauncher jobLauncher;
    private final JobRepository jobRepository;

}

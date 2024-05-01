package com.kickoff.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.kickoff.batch", "com.kickoff.domain"})
@EntityScan(basePackages = {"com.kickoff.domain"})
@EnableJpaRepositories(basePackages = {"com.kickoff.domain"})
@SpringBootApplication
@PropertySource({"classpath:application.yml"})
public class KickOffBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(KickOffBatchApplication.class, args);
    }

}

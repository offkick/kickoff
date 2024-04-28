package com.kickoff.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.kickoff.domain"})
@EnableJpaRepositories(basePackages = {"com.kickoff.domain"})
@SpringBootApplication
@PropertySource({"classpath:application.yml"})
public class KickOffAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(KickOffAdminApplication.class, args);
    }
}

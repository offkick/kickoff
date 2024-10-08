package com.kickoff.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.kickoff.core", "com.kickoff.admin"})
@EntityScan(basePackages = {"com.kickoff.core"})
@EnableJpaRepositories(basePackages = {"com.kickoff.core"})
@SpringBootApplication
@PropertySource({"classpath:application.yml"})
public class KickOffAdminApplication {


    public static void main(String[] args) {
        SpringApplication.run(KickOffAdminApplication.class, args);
    }
}

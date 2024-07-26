package com.kickoff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableCaching
@EntityScan(basePackages = {"com.kickoff.core"})
@EnableJpaRepositories(basePackages = {"com.kickoff.core"})
@SpringBootApplication
@PropertySource({"classpath:application.yml"})
public class KickOffApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(KickOffApiApplication.class, args);
    }
}

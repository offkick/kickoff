package com.kickoff.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients
@ComponentScan(basePackages = {"com.kickoff.batch", "com.kickoff.core"})
@EntityScan(basePackages = {"com.kickoff.core"})
@EnableJpaRepositories(basePackages = {"com.kickoff.core"})
@SpringBootApplication
@EnableScheduling
@PropertySource({"classpath:application.yml"})
public class KickOffBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(KickOffBatchApplication.class, args);
    }

}

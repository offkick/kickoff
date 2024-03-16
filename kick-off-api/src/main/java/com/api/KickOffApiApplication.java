package com.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(scanBasePackages = {"com.api", "com.domain"})
@PropertySource({"classpath:application-domain.yml"})
public class KickOffApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(KickOffApiApplication.class, args);
    }
}

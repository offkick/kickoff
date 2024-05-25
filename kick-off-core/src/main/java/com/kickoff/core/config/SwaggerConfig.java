package com.kickoff.core.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@OpenAPIDefinition(
        info = @Info(title = "Kick Off",
                description = "kick off api명세",
                version = "v1"))
@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi kickoffOpenApi() {
        String[] paths = {"/**"};

        return GroupedOpenApi.builder()
                .group("KICKOFF API v1")
                .pathsToMatch(paths)
                .build();
    }
}

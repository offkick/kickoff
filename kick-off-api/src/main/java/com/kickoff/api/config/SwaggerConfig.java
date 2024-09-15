package com.kickoff.api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
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
        String[] paths = {"/api/**"};

        return GroupedOpenApi.builder()
                .group("kick-off")
                .pathsToMatch(paths)
                .pathsToExclude(
                        "/api/survey/**",
                        "/api/search/soccer-player/**",
                        "/swagger-resources/**",
                        "/swagger-config",
                        "/swagger-ui.html",
                        "/api-docs",
                        "/webjars/**")
                .build();
    }

    @Bean
    public OpenAPI api()
    {
        SecurityScheme apiKey = new SecurityScheme()
                .type(SecurityScheme.Type.APIKEY)
                .in(SecurityScheme.In.HEADER)
                .name("Authorization")
                .scheme("Bearer")
                .bearerFormat("JWT");

        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList("Bearer Token");

        return new OpenAPI()
                .components(new Components().addSecuritySchemes("Bearer Token", apiKey))
                .addSecurityItem(securityRequirement);
    }

}

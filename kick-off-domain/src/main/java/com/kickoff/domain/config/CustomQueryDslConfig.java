package com.kickoff.domain.config;

import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomQueryDslConfig {
    private final EntityManager entityManager;

    public CustomQueryDslConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Bean
    public JPAQueryFactory jpaQueryFactory()
    {
        return new JPAQueryFactory(JPQLTemplates.DEFAULT, entityManager);
    }
}

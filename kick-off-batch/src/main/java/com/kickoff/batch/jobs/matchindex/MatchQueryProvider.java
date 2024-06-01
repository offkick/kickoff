package com.kickoff.batch.jobs.matchindex;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.database.orm.AbstractJpaQueryProvider;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MatchQueryProvider extends AbstractJpaQueryProvider {
    private final EntityManager entityManager;

    @Override
    public Query createQuery()
    {
        Query query = entityManager
                .createQuery("select lg from LeagueTeam lg");

//        query.setParameter("season", season);
        return query;
    }

    @Override
    public void afterPropertiesSet() throws Exception
    {
    }
}

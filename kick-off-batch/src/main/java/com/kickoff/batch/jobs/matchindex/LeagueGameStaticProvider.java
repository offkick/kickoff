package com.kickoff.batch.jobs.matchindex;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.database.orm.AbstractJpaQueryProvider;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LeagueGameStaticProvider extends AbstractJpaQueryProvider {
    private final EntityManager entityManager;

    @Override
    public Query createQuery()
    {
        return entityManager.createQuery("select lg from LeagueGame lg order by leagueGameId");
    }

    @Override
    public void afterPropertiesSet() throws Exception
    {
    }
}

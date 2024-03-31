package com.kickoff.api.elasticsearch.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.TotalHits;
import com.kickoff.api.elasticsearch.index.SoccerPlayerIndex;
import com.kickoff.api.elasticsearch.web.ElasticSearchFoundException;
import com.kickoff.api.elasticsearch.web.SoccerPlayerSearchDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class SoccerPlayerElasticSearchService {
    private final String SOCCER_PLAYER_INDEX = "soccer-player";
    private final ElasticsearchClient client;

    public void search(SoccerPlayerSearchDto soccerPlayerSearchDto)
    {
        try {

            SearchResponse<SoccerPlayerIndex> response = client.search(s -> s.index(SOCCER_PLAYER_INDEX)
                    .query(q -> q.term(t->t.field("playerName").value("손"))),SoccerPlayerIndex.class);
            
            TotalHits totalHits = response.hits().total();

            List<Hit<SoccerPlayerIndex>> hits =  response.hits().hits();

            for (Hit<SoccerPlayerIndex> hit : hits)
            {
                log.info("result = {}", hit.source());
            }
        }catch (Exception e) {
            log.error("~~~~");
        }
    }

    public void searchPlayerById(Long playerId)
    {
        GetResponse<SoccerPlayerIndex> response;
        try
        {
            response = client.get(
                    q -> q.index(SOCCER_PLAYER_INDEX).id(String.valueOf(playerId)), SoccerPlayerIndex.class
            );
        } catch (IOException e)
        {
            throw new ElasticSearchFoundException("엘라스틱 검색 중 에러 발생");
        }

        if (response.found())
        {
            SoccerPlayerIndex soccerPlayerIndex = response.source();
            log.info("Response = " + soccerPlayerIndex);
        }
        else
        {
            throw new IllegalArgumentException("없는 선수입니다.");
        }
    }
}

package com.kickoff.api.controller.team.league;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.kickoff.api.controller.team.league.dto.CreateLeagueTeamRequest;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@Slf4j
@RequestMapping("/api/league")
@RequiredArgsConstructor
@RestController
public class LeagueController {
}

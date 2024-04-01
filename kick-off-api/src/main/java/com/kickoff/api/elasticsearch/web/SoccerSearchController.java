package com.kickoff.api.elasticsearch.web;

// soccer 인덱스 정의
// Team 검색
//
// index : {
//    playerId: 1,
//    teamName: "토트넘",
//    teamId: 2,
//    playerName: "손흥민",
//    position : "FORWARD",
//    nation : "KOREA",
//    age : 32,
//
// }

import com.kickoff.api.elasticsearch.service.SoccerPlayerElasticSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/search/soccer-player")
public class SoccerSearchController {
    private final SoccerPlayerElasticSearchService soccerPlayerElasticSearchService;

    @GetMapping
    public String searchPlayer(@RequestParam(value = "keyword") String keyword)
    {
        log.info("keyword :{}", keyword);
        soccerPlayerElasticSearchService.search(keyword);
        return "SUCCESS";
    }

    @GetMapping("/{playerId}")
    public String searchPlayerById(@PathVariable Long playerId)
    {
        soccerPlayerElasticSearchService.searchPlayerById(playerId);
        return "SUCCESS";
    }
}

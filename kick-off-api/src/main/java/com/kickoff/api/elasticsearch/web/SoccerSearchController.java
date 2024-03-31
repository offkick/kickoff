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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/search/soccer-player")
public class SoccerSearchController {
    private final SoccerPlayerElasticSearchService soccerPlayerElasticSearchService;

    @GetMapping
    public String searchPlayer(SoccerPlayerSearchDto soccerPlayerSearchDto)
    {
        soccerPlayerElasticSearchService.search(soccerPlayerSearchDto);
        return "SUCCESS";
    }

    @GetMapping("/{playerId}")
    public String searchPlayerById(@PathVariable Long playerId)
    {
        soccerPlayerElasticSearchService.searchPlayerById(playerId);
        return "SUCCESS";
    }
}

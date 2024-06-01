package com.kickoff.batch.config.feign;

import com.kickoff.batch.config.feign.api.CompetitionTeamsResponse;
import com.kickoff.batch.config.feign.api.MatchResultResponse;
import com.kickoff.batch.config.feign.api.MatchesFeignResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "soccerApi", url = "https://api.football-data.org", configuration = FeignCustomConfig.class)
public interface SoccerApiFeign {

    @GetMapping("/v4/competitions/CL/matches")
    MatchesFeignResponse getLatestClMatch();

    @GetMapping("/v4/competitions/{competition}/teams")
    CompetitionTeamsResponse getCompetitionTeams(@PathVariable(value = "competition") String competition,
                                                 @RequestParam(required = true, name = "season") String season);

    @GetMapping("/v4/matches")
    MatchResultResponse getMatchResultResponse(
            @RequestParam(name = "competitions") String competitions,
            @RequestParam(name = "dateFrom") String dateFrom,
            @RequestParam(name = "dateTo") String dateTo
    );

}

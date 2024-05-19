package com.kickoff.batch.config.feign.api;

import com.kickoff.batch.config.feign.FeignCustomConfig;
import com.kickoff.batch.config.feign.api.dto.CompetitionTeamsResponse;
import com.kickoff.batch.config.feign.api.dto.MatchesFeignResponse;
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
}

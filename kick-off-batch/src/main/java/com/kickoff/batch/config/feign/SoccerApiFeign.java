package com.kickoff.batch.config.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "soccerApi", url = "https://api.football-data.org", configuration = FeignCustomConfig.class)
public interface SoccerApiFeign {

    @GetMapping("/v4/competitions/CL/matches")
    MatchesResponse matches();
}

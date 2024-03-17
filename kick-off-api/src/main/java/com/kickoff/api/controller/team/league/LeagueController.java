package com.kickoff.api.controller.team.league;

import com.kickoff.api.controller.team.league.dto.CreateLeagueTeamRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/league")
@RequiredArgsConstructor
@RestController
public class LeagueController {

    @PostMapping("/team")
    public void test(@RequestBody CreateLeagueTeamRequest request)
    {

    }
}

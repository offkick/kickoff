package com.kickoff.api.controller;

import com.kickoff.domain.team.league.LeagueTeam;
import com.kickoff.domain.team.league.LeagueTeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class TestController {
    private final LeagueTeamRepository leagueTeamRepository;

    @GetMapping("/api")
    public void test(){
        List<LeagueTeam> all = leagueTeamRepository.findAll();
        System.out.println("~~~~~~~");
        leagueTeamRepository.save(new LeagueTeam());
    }

}

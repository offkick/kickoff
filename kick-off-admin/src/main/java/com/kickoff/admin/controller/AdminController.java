package com.kickoff.admin.controller;

import com.kickoff.core.soccer.team.league.LeagueTeam;
import com.kickoff.core.soccer.team.league.service.LeagueTeamService;
import com.kickoff.core.soccer.team.league.service.dto.LeagueTeamDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/admin")
@Slf4j
public class AdminController {
    private final LeagueTeamService leagueTeamService;

    @GetMapping
    public String admin()
    {
        return "page/main";
    }

    @GetMapping("/player")
    public String player(Model model)
    {
        model.addAttribute("leagueTeam",new LeagueTeam());
        List<LeagueTeamDTO> list = leagueTeamService.findAll();
        model.addAttribute("leagueteam",list);
        return "page/player";
    }

    @GetMapping("/vote")
    public String vote()
    {
        return "page/checkout";
    }

    @GetMapping("/game")
    public String game()
    {
        return "page/game";
    }
}

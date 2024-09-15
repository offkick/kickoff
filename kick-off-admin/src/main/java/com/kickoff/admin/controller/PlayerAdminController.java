package com.kickoff.admin.controller;

import com.kickoff.admin.service.PlayerAdminService;
import com.kickoff.admin.service.dto.*;
import com.kickoff.core.soccer.player.Player;
import com.kickoff.core.soccer.player.dto.PlayerSearchCondition;
import com.kickoff.core.soccer.league.game.dto.GameSearchCondition;
import com.kickoff.core.soccer.league.service.LeagueTeamService;
import com.kickoff.core.soccer.league.service.dto.LeagueTeamDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class PlayerAdminController {

    private final PlayerAdminService playerAdminService;
    private final LeagueTeamService leagueTeamService;

    @PostMapping("/players")
    public String save(@ModelAttribute CreatePlayerAdminRequest request)
    {
        playerAdminService.save(request);
        return "page/player";
    }

    @GetMapping("/all-players")
    public String allPlayers(
            @ModelAttribute PlayerSearchCondition condition,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            Model model
    ) {
        model.addAttribute("size", size);
        Pageable pageable = PageRequest.of(page, size);
        Page<FindPlayerResponses> players= playerAdminService.findPlayers(condition,pageable);
        model.addAttribute("players",players);
        model.addAttribute("condition",condition);
        List<LeagueTeamDTO> list = leagueTeamService.findAll();
        model.addAttribute("leagueteam",list);

        pageModelPut(players, model);

        return "page/allPlayers";
    }

    private void pageModelPut(Page<FindPlayerResponses> players, Model model)
    {
        model.addAttribute("totalCount", players.getTotalElements());
        model.addAttribute("number", players.getPageable().getPageNumber());
        model.addAttribute("maxPage",5);
        model.addAttribute("totalPages",players.getTotalPages());
    }

    @GetMapping("/all-players/{playerId}")
    public String getPlayer(@PathVariable("playerId") Long id,Model model)
    {
        Player player = playerAdminService.findPlayer(id);
        model.addAttribute("player",player);

        return "page/getPlayer";
    }

    @GetMapping("/games")
    public String allGames(
            @ModelAttribute GameSearchCondition condition,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            Model model
    ) {
        model.addAttribute("size", size);
        Pageable pageable = PageRequest.of(page, size);
        Page<FindLeagueGameResponses> leagueGames = playerAdminService.findLeagueGames(condition, pageable);
        List<LeagueTeamDto> leagueResponseList = playerAdminService.findAllLeagueTeam();
        model.addAttribute("league", leagueResponseList);


        model.addAttribute("leagueGames",leagueGames);
        model.addAttribute("condition",condition);

        pageLeaguePut(leagueGames, model);

        return "page/game";
    }

    private void pageLeaguePut(Page<FindLeagueGameResponses> leagueGames, Model model)
    {
        model.addAttribute("totalCount", leagueGames.getTotalElements());
        model.addAttribute("number", leagueGames.getPageable().getPageNumber());
        model.addAttribute("maxPage",5);
        model.addAttribute("totalPages",leagueGames.getTotalPages());
    }
}

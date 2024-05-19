package com.kickoff.admin.controller;

import com.kickoff.admin.service.PlayerAdminService;
import com.kickoff.admin.service.dto.CreatePlayerAdminRequest;
import com.kickoff.domain.soccer.player.Player;
import com.kickoff.domain.soccer.player.PlayerRepositoryImpl;
import com.kickoff.domain.soccer.player.dto.PlayerSearchCondition;
import com.kickoff.domain.soccer.team.league.LeagueTeam;
import com.kickoff.domain.soccer.team.league.service.LeagueTeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static io.micrometer.common.KeyValues.of;

@RequiredArgsConstructor
@Controller
public class PlayerAdminController {

    private final PlayerAdminService playerAdminService;
    private final LeagueTeamService leagueTeamService;
    private final PlayerRepositoryImpl playerRepository;

    @PostMapping("/players")
    public String save(@ModelAttribute CreatePlayerAdminRequest request)
    {
        Long save = playerAdminService.save(request);
        return "page/player";
    }

    @GetMapping("/admin/all-players")
    public String allPlayers(@ModelAttribute PlayerSearchCondition condition,
                             @RequestParam(name = "page", defaultValue = "0") int page,
                             @RequestParam(name = "size", defaultValue = "10") int size,
                             Model model)
    {
        model.addAttribute("size", size);

//        List<Player> searchPlayers = playerAdminService.findPlayer(condition.playerName(), condition.national(), condition.leagueTeamId());

        Pageable pageable = PageRequest.of(page, size);
        Page<Player> players= playerRepository.searchPage(condition, pageable);

//        List<Player> players = playerList.getContent();
//        List<Player> allPlayers = playerAdminService.findAllPlayers();
        model.addAttribute("players",players);
//        List<String> playerName = players.stream()
//                .map(x -> x.getPlayerName())
//                .collect(Collectors.toList());
//        List<String> national = players.stream()
//                .map(x -> x.getNational())
//                .collect(Collectors.toList());
//        List<String> leagueTeamName = players.stream()
//                .map(x -> x.getLeagueTeam().getLeagueTeamName())
//                .collect(Collectors.toList());
//        List<PlayerPosition> positions = players.stream()
//                .map(x -> x.getPosition())
//                .collect(Collectors.toList());
//        model.addAttribute("playerName",playerName);
//        model.addAttribute("national",national);
//        model.addAttribute("leagueTeamName",leagueTeamName);
//        model.addAttribute("positions", positions);


//
//        model.addAttribute("searchPlayers",searchPlayers);
        model.addAttribute("condition",condition);
//        model.addAttribute("leagueTeam",new LeagueTeam());
        List<LeagueTeam> list = leagueTeamService.findAll();
        model.addAttribute("leagueteam",list);

        pageModelPut(players, model);

        return "page/allPlayers";
    }
    private void pageModelPut(Page<Player> players, Model model) {
        model.addAttribute("totalCount", players.getTotalElements());
        model.addAttribute("number", players.getPageable().getPageNumber());
        model.addAttribute("maxPage",5);
        model.addAttribute("totalPages",players.getTotalPages());


    }


    @GetMapping("/admin/all-players/{playerId}")
    public String getPlayer(@PathVariable("playerId") Long id,Model model)
    {
        Player player = playerAdminService.findPlayer(id);
        model.addAttribute("player",player);
        System.out.println(player);

        return "page/getPlayer";
    }
}

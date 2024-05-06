package com.kickoff.admin.controller;

import com.kickoff.admin.service.PlayerAdminService;
import com.kickoff.admin.service.dto.CreatePlayerAdminRequest;
import com.kickoff.domain.soccer.player.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class PlayerAdminController {

    private final PlayerAdminService playerAdminService;

    @PostMapping("/players")
    public String save(@ModelAttribute CreatePlayerAdminRequest request)
    {
        Long save = playerAdminService.save(request);
        return "page/player";
    }

    @RequestMapping("/admin/allPlayers")
    public String allPlayers(Model model)
    {
        List<Player> allPlayers = playerAdminService.findAllPlayers();
        model.addAttribute("allPlayers",allPlayers);
        System.out.println(allPlayers);

        return "page/allPlayers";
    }
}

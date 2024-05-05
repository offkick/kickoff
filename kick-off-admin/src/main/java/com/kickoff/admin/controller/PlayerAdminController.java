package com.kickoff.admin.controller;

import com.kickoff.admin.service.PlayerAdminService;
import com.kickoff.domain.soccer.player.dto.CreatePlayerRequest;
import com.kickoff.domain.soccer.player.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class PlayerAdminController {

    private final PlayerService playerService;

    @PostMapping("/players")
    public String save(@ModelAttribute CreatePlayerRequest request)
    {
        Long save = playerService.save(request);
        return "page/player";
    }
}

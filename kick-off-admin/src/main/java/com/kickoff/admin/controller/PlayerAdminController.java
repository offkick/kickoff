package com.kickoff.admin.controller;

import com.kickoff.admin.service.PlayerAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class PlayerAdminController {

    private final PlayerAdminService playerAdminService;

    @PostMapping
    @ResponseBody
    public void save()
    {
        playerAdminService.save();
    }
}

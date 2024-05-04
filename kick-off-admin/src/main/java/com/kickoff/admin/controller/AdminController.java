package com.kickoff.admin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/admin")
@Slf4j
public class AdminController {

    @GetMapping
    public String admin()
    {
        return "page/main";
    }

    @GetMapping("/player")
    public String plyer()
    {
        return "page/player";
    }

    @GetMapping("/vote")
    public String vote()
    {
        return "page/vote";
    }

    @GetMapping("/game")
    public String game()
    {
        return "page/game";
    }
}

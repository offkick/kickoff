package com.kickoff.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping(value = "/admin")
@Slf4j
public class AdminController {
    @GetMapping
    public String admin()
    {
        return "page/main";
    }
}

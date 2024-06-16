package com.kickoff.api.controller.team.league;

import com.kickoff.api.service.soccer.team.league.ApiLeagueService;
import com.kickoff.api.service.soccer.team.league.dto.FindLeagueResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.List;


@Tag(name = "리그 컨트롤러", description = "리그 관련 리소스 CRUD")
@Slf4j
@Tag(name = "리그 컨트롤러", description = "리그 관련 리소스 검색/변경")
@RequestMapping("/api/league")
@RequiredArgsConstructor
@RestController
public class LeagueController {

    private final ApiLeagueService apiLeagueService;

    @GetMapping("/all")
    public List<FindLeagueResponseDto> findAllLeagues()
    {
        return apiLeagueService.findAllLeagues();
    }
}

package com.kickoff.api.controller.team.league;

import com.kickoff.api.service.soccer.team.league.ApiLeagueService;
import com.kickoff.api.service.soccer.team.league.dto.FindLeagueResponseDto;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@Slf4j
@RequestMapping("/api/league")
@RequiredArgsConstructor
@RestController
public class LeagueController {

    private final ApiLeagueService apiLeagueService;

    @GetMapping("/all")
    public List<FindLeagueResponseDto> findAllLeagues(){
        return apiLeagueService.findAllLeagues();
    }
}

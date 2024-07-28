package com.kickoff.api.controller.team.league;

import com.kickoff.api.service.soccer.team.league.LeagueFindService;
import com.kickoff.api.service.soccer.team.league.dto.FindLeagueResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Tag(name = "리그 컨트롤러", description = "리그 관련 리소스 CRUD")
@Slf4j
@RequestMapping("/league")
@RequiredArgsConstructor
@RestController
public class LeagueController {

    private final LeagueFindService leagueFindService;

    @GetMapping("/all")
    public List<FindLeagueResponse> findAllLeagues()
    {
        return leagueFindService.findAllLeagues();
    }
}

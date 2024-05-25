package com.kickoff.api.controller.team.league.game;

import com.kickoff.api.service.soccer.team.league.ApiLeagueService;
import com.kickoff.api.service.soccer.team.league.dto.FindLeagueGameResponseDto;
import com.kickoff.api.service.soccer.team.league.game.ApiLeagueGameFindService;
import com.kickoff.core.soccer.team.league.game.dto.GameSearchCondition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api/leagueGame")
@RequiredArgsConstructor
@RestController
public class LeagueGameController {
    @Autowired
    ApiLeagueGameFindService apiLeagueService;

    @GetMapping("/all")
    public Page<FindLeagueGameResponseDto> searchGames(GameSearchCondition condition,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "10") int size)
    {
        Pageable pageable = PageRequest.of(page, size);
        return apiLeagueService.findLeagueGames(condition,pageable);
    }
}

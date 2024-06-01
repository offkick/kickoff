package com.kickoff.api.controller.team.league.game;


import com.kickoff.api.service.soccer.team.league.dto.FindLeagueGameResponseDto;
import com.kickoff.api.service.soccer.team.league.game.ApiLeagueGameFindService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@Tag(name = "리그 게임 컨트롤러", description = "리그 게임 관련 리소스 CRUD")
@Slf4j
@RequestMapping("/api/leagueGame")
@RequiredArgsConstructor
@RestController
public class LeagueGameController {

    private final ApiLeagueGameFindService apiLeagueGameFindService;

    @GetMapping("/all")
    public Page<FindLeagueGameResponseDto> searchGames(
            @RequestParam("startDate") LocalDate startDate,
            @RequestParam("endDate") LocalDate endDate,
            @RequestParam("leagueId") Long leagueId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return apiLeagueGameFindService.findLeagueGames(startDate, endDate, leagueId,pageable);
    }
}

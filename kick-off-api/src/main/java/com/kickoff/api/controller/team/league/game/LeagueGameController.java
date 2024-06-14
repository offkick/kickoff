package com.kickoff.api.controller.team.league.game;


import com.kickoff.api.controller.team.league.dto.FindLeagueGamePlayerResponseDto;
import com.kickoff.api.controller.team.league.dto.DateLeagueGameResponse;
import com.kickoff.api.service.soccer.team.league.game.ApiLeagueGameFindService;
import com.kickoff.core.soccer.team.league.game.LeagueGameQuerydslRepository;
import com.kickoff.core.soccer.team.league.game.dto.FindGameCond;
import com.kickoff.core.soccer.team.league.game.dto.FindLeagueGamesResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Tag(name = "리그 게임 컨트롤러", description = "리그 게임 관련 리소스 검색/변경")
@Slf4j
@RequestMapping("/api/leagueGame")
@RequiredArgsConstructor
@RestController
public class LeagueGameController {

    private final ApiLeagueGameFindService apiLeagueGameFindService;
    private final LeagueGameQuerydslRepository leagueGameQuerydslRepository;

    @GetMapping("/all")
    public FindLeagueGamesResponse searchGames(
            @RequestParam("startDate") LocalDate startDate,
            @RequestParam("endDate") LocalDate endDate,
            @RequestParam("leagueId") Long leagueId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        FindGameCond cond = new FindGameCond(
                startDate,
                endDate,
                leagueId,
                PageRequest.of(page,size)
        );
        return leagueGameQuerydslRepository.findLeagueGames(cond);

    }

    @GetMapping("/{leagueGameId}")
    public FindLeagueGamePlayerResponseDto findLeagueGamePlayer(@PathVariable(value="leagueGameId") Long leagueGameId)
    {
        return apiLeagueGameFindService.findByLeagueGameId(leagueGameId);
    }

    @GetMapping("/date/{targetDate}")
    public DateLeagueGameResponse findLeagueGameByDate(@PathVariable String targetDate)
    {
        return apiLeagueGameFindService.findLeagueGameByDate(LocalDate.parse(targetDate));
    }
}

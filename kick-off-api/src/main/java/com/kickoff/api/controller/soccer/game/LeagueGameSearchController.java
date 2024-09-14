package com.kickoff.api.controller.soccer.game;


import com.kickoff.api.controller.soccer.league.dto.DateLeagueGameResponse;
import com.kickoff.api.controller.soccer.league.dto.FindLeagueGamePlayerResponse;
import com.kickoff.api.controller.soccer.league.dto.SeasonLeagueGameResponse;
import com.kickoff.api.service.soccer.team.league.game.LeagueGameFindService;
import com.kickoff.core.soccer.team.league.game.dto.FindGameCond;
import com.kickoff.core.soccer.team.league.game.dto.FindLeagueGamesResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;

@Tag(name = "리그 게임 컨트롤러", description = "리그 게임 관련 리소스 검색")
@Slf4j
@RequestMapping("/league-game/search")
@RequiredArgsConstructor
@RestController
public class LeagueGameSearchController {
    private final LeagueGameFindService leagueGameFindService;

    @GetMapping
    @Parameters({
            @Parameter(name = "startDate", description = "검색 시작 날짜"),
            @Parameter(name = "endDate", description = "검색 끝 날짜"),
            @Parameter(name = "leagueId", description = "리그id"),
    })
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
        return leagueGameFindService.searchGames(cond);
    }

    @GetMapping("/{leagueGameId}")
    public FindLeagueGamePlayerResponse findLeagueGamePlayer(@PathVariable(value="leagueGameId") Long leagueGameId)
    {
        return leagueGameFindService.findByLeagueGameId(leagueGameId);
    }

    @GetMapping("/date")
    public DateLeagueGameResponse findLeagueGameByDate(@RequestParam String targetDate)
    {
        return leagueGameFindService.findLeagueGameByDate(LocalDate.parse(targetDate));
    }

    @GetMapping("/{leagueId}/{yearMonth}")
    public SeasonLeagueGameResponse findLeagueGameBySeason(@PathVariable Long leagueId, @PathVariable String yearMonth)
    {
        return leagueGameFindService.findLeagueGameBySeason(leagueId,YearMonth.parse(yearMonth));
    }

    @GetMapping("/team/{leagueTeamId}/{yearMonth}")
    public SeasonLeagueGameResponse findLeagueTeamBySeason(@PathVariable Long leagueTeamId, @PathVariable String yearMonth)
    {
        return leagueGameFindService.findLeagueTeamBySeason(leagueTeamId,YearMonth.parse(yearMonth));
    }
    @GetMapping("/team/{leagueTeamId}")
    public FindLeagueGamesResponse findLeagueTeamGame(@PathVariable(value = "leagueTeamId") Long leagueGameTeamId, Pageable pageable)
    {
        return leagueGameFindService.findLeagueTeamGame(leagueGameTeamId, pageable);
    }
}

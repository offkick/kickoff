package com.kickoff.api.controller.team.league.game;


import com.kickoff.api.controller.team.league.dto.DateLeagueGameResponse;
import com.kickoff.api.controller.team.league.dto.FindLeagueGamePlayerResponse;
import com.kickoff.api.controller.team.league.dto.SeasonLeagueGameResponse;
import com.kickoff.api.service.soccer.team.league.game.ApiLeagueGameFindService;
import com.kickoff.core.soccer.team.league.Season;
import com.kickoff.core.soccer.team.league.game.LeagueGameQuerydslRepository;
import com.kickoff.core.soccer.team.league.game.dto.FindGameCond;
import com.kickoff.core.soccer.team.league.game.dto.FindLeagueGamesResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;

@Tag(name = "리그 게임 컨트롤러", description = "리그 게임 관련 리소스 검색/변경")
@Slf4j
@RequestMapping("/leagueGame")
@RequiredArgsConstructor
@RestController
public class LeagueGameController {

    private final ApiLeagueGameFindService apiLeagueGameFindService;
    private final LeagueGameQuerydslRepository leagueGameQuerydslRepository;

    @GetMapping("/all")
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
        return leagueGameQuerydslRepository.findLeagueGames(cond);

    }

    @GetMapping("/{leagueGameId}")
    public FindLeagueGamePlayerResponse findLeagueGamePlayer(@PathVariable(value="leagueGameId") Long leagueGameId)
    {
        return apiLeagueGameFindService.findByLeagueGameId(leagueGameId);
    }

    @GetMapping("/date/{targetDate}")
    public DateLeagueGameResponse findLeagueGameByDate(@PathVariable String targetDate)
    {
        return apiLeagueGameFindService.findLeagueGameByDate(LocalDate.parse(targetDate));
    }

    @GetMapping("/league/{leagueId}/{yearMonth}")
    public SeasonLeagueGameResponse findLeagueGameBySeason(@PathVariable Long leagueId, @PathVariable String yearMonth)
    {
        return apiLeagueGameFindService.findLeagueGameBySeason(leagueId,YearMonth.parse(yearMonth));
    }
}

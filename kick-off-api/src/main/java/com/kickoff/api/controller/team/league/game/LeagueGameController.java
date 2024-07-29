package com.kickoff.api.controller.team.league.game;

import com.kickoff.core.soccer.team.league.game.dto.FindLeagueGamesResponse;
import com.kickoff.core.soccer.team.league.service.LeagueGameService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "리그 게임 컨트롤러", description = "리그 게임 관련 리소스 검색")
@Slf4j
@RequestMapping("/league-game")
@RequiredArgsConstructor
@RestController
public class LeagueGameController {
    private final LeagueGameService leagueGameService;

    @GetMapping("/{leagueTeamId}")
    public FindLeagueGamesResponse leagueTeamGame(@PathVariable(value = "leagueTeamId") Long leagueGameTeamId, Pageable pageable)
    {
        return leagueGameService.leagueTeamGames(leagueGameTeamId,pageable);
    }
}

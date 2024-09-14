package com.kickoff.api.controller.team.league;

import com.kickoff.api.controller.team.league.dto.TeamRankInfo;
import com.kickoff.api.service.soccer.team.league.ApiLeagueTeamService;
import com.kickoff.api.service.soccer.team.league.LeagueFindService;
import com.kickoff.api.service.soccer.team.league.TeamStandingService;
import com.kickoff.api.service.soccer.team.league.dto.FindLeagueResponse;
import com.kickoff.api.service.soccer.team.league.dto.LeagueTeamResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "리그 컨트롤러", description = "리그 관련 리소스 CRUD")
@Slf4j
@RequestMapping("/league")
@RequiredArgsConstructor
@RestController
public class LeagueController {
    private final LeagueFindService leagueFindService;
    private final ApiLeagueTeamService leagueTeamService;
    private final TeamStandingService teamStandingService;

    @GetMapping("/all")
    public List<FindLeagueResponse> findAllLeagues()
    {
        return leagueFindService.findAllLeagues();
    }

    @GetMapping
    public List<FindLeagueResponse> findAllLeagues(
            @RequestParam String season,
            @RequestParam String competition
    ) {
        return leagueFindService.findAllLeagues(season, competition);
    }

    @GetMapping("/{years}/{leagueId}")
    public List<LeagueTeamResponse> findLeagueTeam(
            @PathVariable(value = "years") String years,
            @PathVariable(value = "leagueId") Long leagueId
    ) {
        return leagueTeamService.findLeagueTeam(years,leagueId);
    }

    @GetMapping("/rank/{leagueId}")
    public TeamRankInfo getTeamRanks(
            @PathVariable(value = "leagueId") Long leagueId,
            @RequestParam(name = "season") String  season
    ) {
        return TeamRankInfo.of(teamStandingService.teamStandings(season, leagueId));
    }
}

package com.kickoff.api.service.soccer.team.league;

import com.kickoff.core.soccer.team.standing.TeamStandingQueryService;
import com.kickoff.core.soccer.team.standing.dto.TeamStandingQueryResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class TeamStandingService {
    private final TeamStandingQueryService teamStandingQueryService;
    public List<TeamStandingQueryResult> teamStandings(String season, Long leagueId)
    {
        return teamStandingQueryService.teamStandings(season, leagueId);
    }
}

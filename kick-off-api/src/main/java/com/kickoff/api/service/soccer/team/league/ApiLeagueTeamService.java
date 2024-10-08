package com.kickoff.api.service.soccer.team.league;

import com.kickoff.api.service.soccer.team.league.dto.LeagueTeamResponse;
import com.kickoff.core.soccer.league.service.LeagueTeamService;
import com.kickoff.core.soccer.league.service.dto.TeamByLeagueDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApiLeagueTeamService {
    private final LeagueTeamService leagueTeamService;

    public List<LeagueTeamResponse> findLeagueTeam(String years, Long leagueId)
    {
        List<TeamByLeagueDTO> leagueTeam = leagueTeamService.findLeagueTeam(years, leagueId);
        return leagueTeam.stream()
                .map(LeagueTeamResponse::of)
                .collect(Collectors.toList());
    }
}
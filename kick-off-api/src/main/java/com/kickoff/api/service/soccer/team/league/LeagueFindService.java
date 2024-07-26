package com.kickoff.api.service.soccer.team.league;

import com.kickoff.api.service.soccer.team.league.dto.FindLeagueResponse;
import com.kickoff.core.soccer.team.league.service.LeagueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class LeagueFindService {
    private final LeagueService leagueService;

    public List<FindLeagueResponse> findAllLeagues()
    {
        return leagueService.findAll().stream()
                .map(FindLeagueResponse::from)
                .collect(Collectors.toList());
    }
}

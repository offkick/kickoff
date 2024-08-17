package com.kickoff.api.service.soccer.team.league;

import com.kickoff.core.soccer.standing.TeamStanding;
import com.kickoff.core.soccer.standing.service.TeamStandingQuerydslRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamStandingQueryService {
    private final TeamStandingQuerydslRepository teamStandingQuerydslRepository;

    @Transactional(readOnly = true)
    public List<TeamStanding> teamStandings(Long matchDay, String season, String competition)
    {
        return null;
    }
}

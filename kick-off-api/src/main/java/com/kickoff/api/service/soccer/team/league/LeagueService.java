package com.kickoff.api.service.soccer.team.league;

import com.kickoff.api.service.soccer.team.league.dto.CreateLeagueServiceRequest;
import com.kickoff.domain.soccer.team.league.League;
import com.kickoff.domain.soccer.team.league.LeagueRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class LeagueService {
    private final LeagueRepository leagueRepository;

    public Long createLeague(CreateLeagueServiceRequest request)
    {
        League league = League.builder()
                .leagueName(request.leagueName())
                .national(request.national())
                .tier(request.tier())
                .build();
        return leagueRepository.save(league).getLeagueId();
    }


}

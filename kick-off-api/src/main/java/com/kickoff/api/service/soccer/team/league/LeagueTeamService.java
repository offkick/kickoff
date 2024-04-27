package com.kickoff.api.service.soccer.team.league;

import com.kickoff.api.service.soccer.team.league.dto.CreateTeamServiceRequest;
import com.kickoff.domain.soccer.team.league.League;
import com.kickoff.domain.soccer.team.league.LeagueRepository;
import com.kickoff.domain.soccer.team.league.LeagueTeamRepository;
import com.kickoff.domain.team.league.LeagueTeam;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LeagueTeamService {
    private final LeagueTeamRepository leagueTeamRepository;
    private final LeagueRepository leagueRepository;

    public Long createLeagueTeam(CreateTeamServiceRequest request)
    {
        League league = leagueRepository.findById(request.leagueId())
                .orElseThrow(IllegalArgumentException::new);

        LeagueTeam leagueTeam = LeagueTeam.builder()
                .leagueTeamName(request.leagueTeamName())
                .teamType(request.teamType())
                .league(league).build();

        return leagueTeamRepository.save(leagueTeam).getLeagueTeamId();
    }


}

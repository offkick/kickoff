package com.kickoff.api.service.soccer.team.league;

import com.kickoff.api.service.soccer.team.league.dto.CreateTeamServiceRequest;
import com.kickoff.domain.soccer.team.league.League;
import com.kickoff.domain.soccer.team.league.LeagueRepository;
import com.kickoff.domain.soccer.team.league.LeagueTeam;
import com.kickoff.domain.soccer.team.league.LeagueTeamRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

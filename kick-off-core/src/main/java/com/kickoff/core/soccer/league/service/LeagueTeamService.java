package com.kickoff.core.soccer.league.service;

import com.kickoff.core.soccer.league.LeagueTeam;
import com.kickoff.core.soccer.league.service.dto.LeagueTeamDTO;
import com.kickoff.core.soccer.league.LeagueTeamQuerydslRepository;
import com.kickoff.core.soccer.league.LeagueTeamRepository;
import com.kickoff.core.soccer.league.service.dto.TeamByLeagueDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class LeagueTeamService {
    private final LeagueTeamRepository leagueTeamRepository;
    private final LeagueTeamQuerydslRepository leagueTeamQuerydslRepository;

    @Transactional(readOnly = true)
    public List<LeagueTeamDTO> findAll()
    {
        return leagueTeamRepository.findAll()
                .stream()
                .map(LeagueTeamDTO::of)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public LeagueTeamDTO findById(Long leagueTeamId)
    {
        LeagueTeam leagueTeam = leagueTeamRepository.findById(leagueTeamId)
                .orElseThrow();

        return LeagueTeamDTO.of(leagueTeam);
    }

     public List<TeamByLeagueDTO> findLeagueTeam(String years, Long leagueId)
    {
        return leagueTeamQuerydslRepository.findTeamByLeague(years,leagueId);
    }
}

package com.kickoff.core.soccer.team.league.service;

import com.kickoff.core.soccer.team.league.LeagueTeamRepository;
import com.kickoff.core.soccer.team.league.service.dto.LeagueTeamDTO;
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

    @Transactional(readOnly = true)
    public List<LeagueTeamDTO> findAll()
    {
        return leagueTeamRepository.findAll()
                .stream()
                .map(LeagueTeamDTO::of)
                .collect(Collectors.toList());
    }
}
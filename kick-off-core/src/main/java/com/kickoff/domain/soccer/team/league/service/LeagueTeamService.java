package com.kickoff.domain.soccer.team.league.service;

import com.kickoff.domain.soccer.team.league.LeagueTeam;
import com.kickoff.domain.soccer.team.league.LeagueTeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LeagueTeamService {
    private final LeagueTeamRepository leagueTeamRepository;
    public List<LeagueTeam> findAll() {
        return leagueTeamRepository.findAll();

    }
}

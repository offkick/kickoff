package com.kickoff.core.soccer.league.service;

import com.kickoff.core.soccer.league.League;
import com.kickoff.core.soccer.league.LeagueRepository;
import com.kickoff.core.soccer.league.dto.LatestLeagueDTO;
import com.kickoff.core.soccer.league.service.dto.LeagueDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeagueService {
    private final LeagueRepository leagueRepository;

    public List<LeagueDTO> findAll()
    {
        return leagueRepository.findAll()
                .stream()
                .map(LeagueDTO::of)
                .collect(Collectors.toList());
    }

    public LeagueDTO findByLeagueId(Long leagueId)
    {
        Optional<League> leagueDTO = leagueRepository.findById(leagueId);

        if (leagueDTO.isEmpty())
        {
            return null;
        }

        League league = leagueDTO.get();
        return LeagueDTO.of(league);
    }

    public LeagueDTO findByLeagueName(String name)
    {
        League league = leagueRepository.findByLeagueName(name).orElseThrow();
        return LeagueDTO.of(league);
    }

    public List<LatestLeagueDTO> findLatestLeague()
    {
        return leagueRepository.findLatestLeague();
    }
}

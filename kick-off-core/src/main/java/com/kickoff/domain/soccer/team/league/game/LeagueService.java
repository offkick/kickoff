package com.kickoff.domain.soccer.team.league.game;

import com.kickoff.domain.soccer.team.league.League;
import com.kickoff.domain.soccer.team.league.LeagueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LeagueService {
    private final LeagueRepository leagueRepository;

    public Optional<League> findByName(String leagueName)
    {
        return leagueRepository.findByLeagueNameLike(leagueName);
    }
}

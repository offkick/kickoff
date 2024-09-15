package com.kickoff.api.service.soccer.team.league;

import com.kickoff.api.controller.soccer.game.LatestLeagueInfo;
import com.kickoff.api.service.soccer.team.league.dto.FindLeagueResponse;
import com.kickoff.core.soccer.league.service.LeagueService;
import com.kickoff.core.soccer.league.service.SeasonService;
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
    private final SeasonService seasonService;

    public List<FindLeagueResponse> findAllLeagues()
    {
        return leagueService.findAll().stream()
                .map(FindLeagueResponse::from)
                .collect(Collectors.toList());
    }

    public List<FindLeagueResponse> findAllLeagues(String season, String competition)
    {
        return leagueService.findAll().stream()
                .filter(s -> s.seasonYear().equals(season))
                .filter(s -> equalLeagueName(competition, s.leagueName()))
                .map(FindLeagueResponse::from)
                .collect(Collectors.toList());
    }

    private boolean equalLeagueName(String competition, String leagueName)
    {
        return switch (competition) {
            case "PL" -> leagueName.equals("PL");
            case "LALIGA" -> leagueName.equals("PD");
            case "LEAGUE1" -> leagueName.equals("FL1");
            case "BUNDESLIGA" -> leagueName.equals("ABL");
            default -> throw new IllegalArgumentException("지원 하지 않는 코드");
        };
    }

    public LatestLeagueInfo latestLeagues()
    {
        // TODO
        leagueService.findByLeagueName("PL")
        return null;
    }
}

package com.kickoff.core.soccer.team.league.service;

import com.kickoff.core.soccer.team.league.game.LeagueGame;
import com.kickoff.core.soccer.team.league.game.LeagueGameQuerydslRepository;
import com.kickoff.core.soccer.team.league.game.LeagueGameRepository;
import com.kickoff.core.soccer.team.league.game.dto.FindLeagueGamesResponse;
import com.kickoff.core.soccer.team.league.service.dto.LeagueGameDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class LeagueGameService {
    private final LeagueGameRepository leagueGameRepository;
    private final LeagueGameQuerydslRepository leagueGameQuerydslRepository;

    public List<LeagueGameDTO> findByGameDateBetween(LocalDateTime start, LocalDateTime end)
    {
        List<LeagueGame> byGameDateBetween = leagueGameRepository.findByGameDateBetween(start, end);
        return byGameDateBetween.stream()
                .map(LeagueGameDTO::of)
                .collect(Collectors.toList());
    }

    public Optional<LeagueGameDTO> findById(Long leagueGameId)
    {
        return leagueGameRepository.findById(leagueGameId).map(LeagueGameDTO::of);
    }

    public List<LeagueGameDTO> findBySeasonBetween(Long leagueId, LocalDateTime startDateTime, LocalDateTime endDateTime)
    {
        return leagueGameRepository.findBySeasonBetween(leagueId, startDateTime, endDateTime)
                .stream()
                .map(LeagueGameDTO::of)
                .collect(Collectors.toList());
    }

    public List<LeagueGameDTO> findByLeagueTeam(Long leagueTeamId, LocalDateTime startDateTime, LocalDateTime endDateTime)
    {
        return leagueGameRepository.findByLeagueTeam(leagueTeamId, startDateTime, endDateTime)
                .stream()
                .map(LeagueGameDTO::of)
                .collect(Collectors.toList());
    }


    public FindLeagueGamesResponse leagueTeamGames(Long leagueTeamId, Pageable pageable)
    {
        return leagueGameQuerydslRepository.findLeagueTeamGame(leagueTeamId,pageable);
    }
}

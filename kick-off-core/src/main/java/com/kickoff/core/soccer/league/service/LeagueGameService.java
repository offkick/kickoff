package com.kickoff.core.soccer.league.service;

import com.kickoff.core.soccer.game.LeagueGame;
import com.kickoff.core.soccer.game.LeagueGameQuerydslRepository;
import com.kickoff.core.soccer.game.LeagueGameRepository;
import com.kickoff.core.soccer.game.dto.FindLeagueGamesResponse;
import com.kickoff.core.soccer.league.service.dto.LeagueGameDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<LeagueGameDTO> findByGameDateBetweenAndLeagueId(LocalDateTime start, LocalDateTime end, Long leagueId)
    {
        List<LeagueGame> byGameDateBetween = leagueGameRepository.findBySeasonBetween(leagueId, start, end);
        return byGameDateBetween.stream()
                .map(LeagueGameDTO::of)
                .collect(Collectors.toList());
    }


    public Optional<LeagueGameDTO> findById(Long leagueGameId)
    {
        LeagueGame leagueGame = leagueGameRepository.findById(leagueGameId).orElse(null);

        if (leagueGame == null)
        {
            return Optional.empty();
        }

        return Optional.of(LeagueGameDTO.of(leagueGame));
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
    public List<LeagueGameDTO> findByLeagueOrTeamAndSeason(Long leagueId, Long leagueTeamId, LocalDateTime startDateTime, LocalDateTime endDateTime)
    {
        return leagueGameRepository.findByLeagueOrTeamAndSeason(leagueId,leagueTeamId, startDateTime, endDateTime)
                .stream()
                .map(LeagueGameDTO::of)
                .collect(Collectors.toList());
    }

    public FindLeagueGamesResponse leagueTeamGames(Long leagueTeamId, Pageable pageable)
    {
        return leagueGameQuerydslRepository.findLeagueTeamGame(leagueTeamId,pageable);
    }

}

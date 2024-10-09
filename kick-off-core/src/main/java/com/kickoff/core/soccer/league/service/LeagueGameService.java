package com.kickoff.core.soccer.league.service;

import com.kickoff.core.soccer.game.LeagueGame;
import com.kickoff.core.soccer.game.LeagueGameQuerydslRepository;
import com.kickoff.core.soccer.game.LeagueGameRepository;
import com.kickoff.core.soccer.game.dto.FindLeagueGamesResponse;
import com.kickoff.core.soccer.league.service.dto.LeagueGameDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class LeagueGameService {
    private final LeagueGameRepository leagueGameRepository;
    private final LeagueGameQuerydslRepository leagueGameQuerydslRepository;

    public List<LeagueGameDTO> findByGameDateBetweenAndLeagueId(LocalDateTime start, LocalDateTime end, Long leagueId)
    {
        List<LeagueGame> byGameDateBetween = leagueGameRepository.findBySeasonBetween(leagueId, start, end);
        return byGameDateBetween.stream()
                .map(LeagueGameDTO::of)
                .collect(Collectors.toList());
    }

    public Optional<LeagueGameDTO> findById(Long leagueGameId)
    {
//        log.info("=====" + leagueGameRepository.findById(leagueGameId).map(LeagueGameDTO::of).);
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
}

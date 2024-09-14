package com.kickoff.api.service.soccer.team.league.game;

import com.kickoff.api.controller.soccer.league.dto.DateLeagueGameResponse;
import com.kickoff.api.controller.soccer.league.dto.FindLeagueGamePlayerResponse;
import com.kickoff.api.controller.soccer.league.dto.SeasonLeagueGameResponse;
import com.kickoff.core.soccer.team.league.game.LeagueGameQuerydslRepository;
import com.kickoff.core.soccer.team.league.game.dto.FindGameCond;
import com.kickoff.core.soccer.team.league.game.dto.FindLeagueGamesResponse;
import com.kickoff.core.soccer.team.league.service.LeagueGameService;
import com.kickoff.core.soccer.team.league.service.dto.LeagueGameDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.List;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class LeagueGameFindService {
    private final LeagueGameService leagueGameService;
    private final LeagueGameQuerydslRepository leagueGameQuerydslRepository;

    public FindLeagueGamesResponse searchGames(FindGameCond cond)
    {
        return leagueGameQuerydslRepository.findLeagueGames(cond);
    }

    public FindLeagueGamePlayerResponse findByLeagueGameId(Long leagueGameId)
    {
        LeagueGameDTO leagueGame = leagueGameService.findById(leagueGameId).orElseThrow(EntityNotFoundException::new);
        return FindLeagueGamePlayerResponse.of(leagueGame);
    }

    public DateLeagueGameResponse findLeagueGameByDate(LocalDate date)
    {
        List<LeagueGameDTO> leagueGames = leagueGameService.findByGameDateBetween(
                date.atStartOfDay(),
                date.atTime(LocalTime.MAX)
        );

        return DateLeagueGameResponse.of(leagueGames);
    }

    public SeasonLeagueGameResponse findLeagueGameBySeason(Long leagueId, YearMonth yearMonth)
    {
        LocalDateTime startDateTime = yearMonth.atDay(1).atStartOfDay();
        LocalDateTime endDateTime = yearMonth.atEndOfMonth().atTime(23, 59, 59);
        List<LeagueGameDTO> leagueGameList = leagueGameService.findBySeasonBetween(leagueId, startDateTime, endDateTime);

        return SeasonLeagueGameResponse.of(leagueGameList);
    }

    public SeasonLeagueGameResponse findLeagueTeamBySeason(Long leagueTeamId, YearMonth yearMonth)
    {
        LocalDateTime startDateTime = yearMonth.atDay(1).atStartOfDay();
        LocalDateTime endDateTime = yearMonth.atEndOfMonth().atTime(23,59,59);
        List<LeagueGameDTO> byLeagueTeam = leagueGameService.findByLeagueTeam(leagueTeamId, startDateTime, endDateTime);
        return SeasonLeagueGameResponse.of(byLeagueTeam);

    }

    public FindLeagueGamesResponse findLeagueTeamGame(Long leagueTeamId, Pageable pageable)
    {
        return leagueGameQuerydslRepository.findLeagueTeamGame(leagueTeamId, pageable);
    }
}

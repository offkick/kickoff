package com.kickoff.api.service.soccer.team.league.game;

import com.kickoff.api.controller.team.league.dto.DateLeagueGameResponse;
import com.kickoff.api.controller.team.league.dto.FindLeagueGamePlayerResponse;
import com.kickoff.api.controller.team.league.dto.SeasonLeagueGameResponse;
import com.kickoff.core.soccer.team.league.game.LeagueGame;
import com.kickoff.core.soccer.team.league.game.LeagueGameRepository;
import com.kickoff.core.soccer.team.league.service.LeagueGameService;
import com.kickoff.core.soccer.team.league.service.dto.LeagueGameDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
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
public class ApiLeagueGameFindService {
    private final LeagueGameService leagueGameService;

    public FindLeagueGamePlayerResponse findByLeagueGameId(Long leagueGameId)
    {
        LeagueGameDTO leagueGame = leagueGameService.findById(leagueGameId).orElseThrow(EntityNotFoundException::new);
        return FindLeagueGamePlayerResponse.of(leagueGame);
    }

    public DateLeagueGameResponse findLeagueGameByDate(LocalDate date)
    {
        LocalTime endTime = LocalTime.of(23, 59, 59);
        List<LeagueGameDTO> leagueGames = leagueGameService.findByGameDateBetween(
                date.atStartOfDay(),
                date.atTime(endTime)
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
}

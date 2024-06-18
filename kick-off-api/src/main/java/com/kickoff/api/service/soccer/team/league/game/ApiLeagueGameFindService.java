package com.kickoff.api.service.soccer.team.league.game;

import com.kickoff.api.controller.team.league.dto.DateLeagueGameResponse;
import com.kickoff.api.controller.team.league.dto.FindLeagueGamePlayerResponse;
import com.kickoff.core.soccer.team.league.game.LeagueGame;
import com.kickoff.core.soccer.team.league.game.LeagueGameRepository;
import com.kickoff.core.soccer.team.league.service.LeagueGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * 경기 아이디를 받아서 경기 상세 조회 findGameById(gameId)
 * 기능 : 경기 조회
 * 경기 일자, 경기 팀 2개, 스코 팀마다 출전 선수 있을거고 (player + game)
 */
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ApiLeagueGameFindService {
    private final LeagueGameRepository leagueGameRepository;
    private final LeagueGameService leagueGameService;
    public FindLeagueGamePlayerResponse findByLeagueGameId(Long leagueGameId)
    {
        LeagueGame leagueGame = leagueGameRepository.findById(leagueGameId).orElseThrow(() -> new IllegalArgumentException());
        return FindLeagueGamePlayerResponse.from(leagueGame);
    }

    public DateLeagueGameResponse findLeagueGameByDate(LocalDate date)
    {
        LocalTime endTime = LocalTime.of(23, 59, 59);
        List<LeagueGame> leagueGames = leagueGameRepository.findByGameDateBetween(
                date.atStartOfDay(),
                date.atTime(endTime)
        );
        return DateLeagueGameResponse.of(leagueGames);
    }
}

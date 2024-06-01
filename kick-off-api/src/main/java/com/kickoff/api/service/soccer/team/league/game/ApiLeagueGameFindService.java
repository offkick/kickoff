package com.kickoff.api.service.soccer.team.league.game;

import com.kickoff.api.service.soccer.team.league.dto.FindLeagueGameResponseDto;
import com.kickoff.core.soccer.team.league.game.LeagueGameRepositoryImpl;
import com.kickoff.core.soccer.team.league.game.dto.FindLeagueGameResponse;
import com.kickoff.core.soccer.team.league.game.dto.GameSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 경기 아이디를 받아서 경기 상세 조회 findGameById(gameId)
 * 기능 : 경기 조회
 * 경기 일자, 경기 팀 2개, 스코 팀마다 출전 선수 있을거고 (player + game)
 */
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ApiLeagueGameFindService {
    private final LeagueGameRepositoryImpl leagueGameRepositoryImpl;

    public Page<FindLeagueGameResponseDto> findLeagueGames(LocalDate startDate, LocalDate endDate, Long leagueId, Pageable pageable) {
        GameSearchCondition condition = new GameSearchCondition(startDate, endDate, leagueId);
        Page<FindLeagueGameResponse> responses = leagueGameRepositoryImpl.searchGame(condition, pageable);

        List<FindLeagueGameResponseDto> convertedResponses = responses.getContent().stream()
                .map(response -> new FindLeagueGameResponseDto(response.leagueGameId(),
                        response.gameDate(),
                        response.count(),
                        response.away(),
                        response.home(),
                        response.score(),
                        response.leagueGameStatus(),
                        response.season(),
                        response.homePlayers(),
                        response.awayPlayers()))
                .collect(Collectors.toList());

        return new PageImpl<>(convertedResponses, pageable, responses.getTotalElements());
    }
}

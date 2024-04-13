package com.kickoff.api.service.soccer.team.league.game;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 경기 아이디를 받아서 경기 상세 조회 findGameById(gameId)
 * 기능 : 경기 조회
 * 경기 일자, 경기 팀 2개, 스코 팀마다 출전 선수 있을거고 (player + game)
 */
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class LeagueGameFindService {
}

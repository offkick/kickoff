package com.kickoff.core.soccer.team.league.service;

import com.kickoff.core.soccer.team.league.dto.FindLeagueGamePlayerResponse;
import com.kickoff.core.soccer.team.league.game.LeagueGame;
import com.kickoff.core.soccer.team.league.game.LeagueGameRepository;
import com.kickoff.core.soccer.team.league.game.LeagueGameRepositoryImpl;
import com.kickoff.core.soccer.team.league.game.dto.FindLeagueGameResponse;
import com.kickoff.core.soccer.team.league.game.dto.GameSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LeagueGameService {

    private final LeagueGameRepositoryImpl leagueGameRepositoryImpl;
    private final LeagueGameRepository leagueGameRepository;
    public Page<FindLeagueGameResponse> findGames(GameSearchCondition gameSearchCondition, Pageable pageable){
        return leagueGameRepositoryImpl.searchGame(gameSearchCondition,pageable);
    }
    public FindLeagueGamePlayerResponse findByLeagueGameId(Long leagueGameId)
    {
        LeagueGame leagueGame = leagueGameRepository.findById(leagueGameId).orElseThrow(() -> new IllegalArgumentException());
        return FindLeagueGamePlayerResponse.from(leagueGame);
    }


}

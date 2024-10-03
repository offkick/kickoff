package com.kickoff.batch.jobs.data.service;

import com.kickoff.batch.config.feign.SoccerApiFeign;
import com.kickoff.core.soccer.league.LeagueRepository;
import com.kickoff.core.soccer.league.LeagueTeamRepository;
import com.kickoff.core.soccer.league.SeasonRepository;
import com.kickoff.core.soccer.game.LeagueGameRepository;
import com.kickoff.core.soccer.player.PlayerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
@Slf4j
public class AllDataDeleteService {
    private final LeagueGameRepository leagueGameRepository;
    private final LeagueTeamRepository leagueTeamRepository;
    private final PlayerRepository playerRepository;
    private final LeagueRepository leagueRepository;
    private final SeasonRepository seasonRepository;
    private final SoccerApiFeign soccerApiFeign;
    @PersistenceContext
    private final EntityManager entityManager;

    public void deleteAllData() {
        leagueGameRepository.deleteAll();
        leagueTeamRepository.deleteAll();
        playerRepository.deleteAll();
        leagueRepository.deleteAll();
        seasonRepository.deleteAll();
        soccerApiFeign.deleteAllData();
    }

}

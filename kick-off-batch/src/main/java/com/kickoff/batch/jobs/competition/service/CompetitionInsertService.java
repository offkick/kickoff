package com.kickoff.batch.jobs.competition.service;

import com.kickoff.batch.config.feign.SoccerApiFeign;
import com.kickoff.batch.config.feign.api.temp.Competitions;
import com.kickoff.core.common.National;
import com.kickoff.core.soccer.team.league.League;
import com.kickoff.core.soccer.team.league.LeagueRepository;
import com.kickoff.core.soccer.team.league.Season;
import com.kickoff.core.soccer.team.league.SeasonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
@Slf4j
public class CompetitionInsertService {
    private final SoccerApiFeign soccerApiFeign;
    private final LeagueRepository leagueRepository;
    private final SeasonRepository seasonRepository;

    public void insertCompetition(String competitions)
    {
        Competitions competitionsData = soccerApiFeign.getCompetitionResponse(competitions);
        log.info("competitions = {}", competitionsData);
        List<com.kickoff.batch.config.feign.api.temp.Season> seasons = competitionsData.seasons();

        for(com.kickoff.batch.config.feign.api.temp.Season season : seasons){
            LocalDate startDate = LocalDate.parse(season.startDate());
            String startYear= String.valueOf(startDate.getYear());
            Season season1 = Season.builder()
                    .years(startYear).build();
            seasonRepository.save(season1);

            League league = League.builder()
                    .leagueName(competitionsData.name())
                    .emblem(competitionsData.emblem())
                    .season(season1)
                    .build();
            leagueRepository.save(league);
        }

    }
}

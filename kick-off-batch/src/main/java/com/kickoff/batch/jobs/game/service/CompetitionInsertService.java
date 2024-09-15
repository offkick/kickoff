package com.kickoff.batch.jobs.game.service;

import com.kickoff.batch.config.feign.SoccerApiFeign;
import com.kickoff.batch.config.feign.api.temp.Competitions;
import com.kickoff.batch.config.feign.api.temp.SeasonDTO;
import com.kickoff.core.soccer.league.*;
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
    private final WinnerRepository winnerRepository;

    public void insertCompetition(String competitions)
    {
        Competitions competitionsData = soccerApiFeign.getCompetitionResponse(competitions);

        List<SeasonDTO> seasons = competitionsData.seasons();

        for(SeasonDTO season : seasons)
        {
            LocalDate startDate = LocalDate.parse(season.startDate());
            String startYear= String.valueOf(startDate.getYear());
            Season findSeason = seasonRepository.findByYears(startYear).orElseThrow();

            Long winnerId = null;
            if (season.winner() != null)
            {
                Long externalTeamId = (long)season.winner().id();
                Winner winner = Winner.builder()
                        .winnerTeamName(season.winner().name())
                        .externalTeamId(externalTeamId)
                        .build();
                winnerRepository.save(winner);
                winnerId = winner.getWinnerId();
            } else {
                winnerId = null;
            }


            League league = League.builder()
                    .leagueName(competitionsData.name())
                    .emblem(competitionsData.emblem())
                    .season(findSeason)
                    .winnerId(winnerId)
                    .startDate(season.startDate())
                    .endDate(season.endDate())
                    .build();
            leagueRepository.save(league);
        }

    }
}

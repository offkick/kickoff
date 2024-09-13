package com.kickoff.batch.jobs.competition.service;

import com.kickoff.batch.config.feign.SoccerApiFeign;
import com.kickoff.batch.config.feign.api.temp.Competitions;
import com.kickoff.core.soccer.team.external.ExternalTeamIdMapping;
import com.kickoff.core.soccer.team.external.ExternalTeamIdMappingRepository;
import com.kickoff.core.soccer.team.league.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

        List<com.kickoff.batch.config.feign.api.temp.Season> seasons = competitionsData.seasons();

        for(com.kickoff.batch.config.feign.api.temp.Season season : seasons)
        {

            LocalDate startDate = LocalDate.parse(season.startDate());
            String startYear= String.valueOf(startDate.getYear());
            Season season1 = Season.builder().years(startYear).build();

            Long winnerId = null;
            if (season.winner() != null) {
                winnerId = (long)season.winner().id();
                Winner winner = Winner.builder()
                        .winnerId(winnerId)
                        .winnerTeamName(season.winner().name())
                        .build();
                winnerRepository.save(winner);
            } else {
                winnerId = null;

            }

            seasonRepository.save(season1);

            League league = League.builder()
                    .leagueName(competitionsData.name())
                    .emblem(competitionsData.emblem())
                    .season(season1)
                    .winnerId(winnerId)
                    .build();
            leagueRepository.save(league);
        }

    }
}

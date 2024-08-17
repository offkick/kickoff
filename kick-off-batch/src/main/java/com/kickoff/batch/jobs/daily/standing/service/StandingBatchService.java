package com.kickoff.batch.jobs.daily.standing.service;

import com.kickoff.batch.config.feign.SoccerApiFeign;
import com.kickoff.batch.config.feign.api.temp.StandingResponse;
import com.kickoff.batch.config.feign.api.temp.Standings;
import com.kickoff.core.soccer.standing.TeamStanding;
import com.kickoff.core.soccer.standing.TeamStandingRepository;
import com.kickoff.core.soccer.team.external.ExternalTeamIdMapping;
import com.kickoff.core.soccer.team.external.ExternalTeamIdMappingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class StandingBatchService {
    private final SoccerApiFeign soccerApiFeign;
    private final ExternalTeamIdMappingRepository externalTeamIdMappingRepository;
    private final TeamStandingRepository teamStandingRepository;

    public void insertStanding(String season, Long matchday, Long leagueId)
    {
        if (teamStandingRepository.existsBySeasonAndRound(season, matchday)) {
            log.info("exists round standing info...");
            return ;
        }

        StandingResponse standings = soccerApiFeign.getStandings(season, matchday);
        for (Standings d : standings.standings())
        {
            if(!d.type().equals("TOTAL")) {
                continue;
            }

            for (Standings.Table t : d.table())
            {
                int externalTeamId = t.team().id();
                ExternalTeamIdMapping externalTeamIdMapping = externalTeamIdMappingRepository.findByExternalTeamId(Long.valueOf(externalTeamId))
                        .orElse(null);

                Long teamId = externalTeamIdMapping.getExternalTeamId();
                TeamStanding save = teamStandingRepository.save(TeamStanding.builder()
                        .rank(t.position())
                        .round(matchday)
                        .teamId(externalTeamIdMapping.getTeamId())
                        .draw(t.draw())
                        .won(t.won())
                        .goalsAgainst(t.goalsAgainst())
                        .goalsFor(t.goalsFor())
                        .season(String.valueOf(season))
                        .lost(t.lost())
                        .build());
            }
        }
    }
}

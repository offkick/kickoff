package com.kickoff.batch.jobs.team.standing.service;

import com.kickoff.batch.config.feign.SoccerApiFeign;
import com.kickoff.batch.config.feign.api.temp.StandingResponse;
import com.kickoff.batch.config.feign.api.temp.Standings;
import com.kickoff.core.soccer.team.standing.TeamStanding;
import com.kickoff.core.soccer.team.standing.TeamStandingRepository;
import com.kickoff.core.soccer.team.external.ExternalTeamIdMapping;
import com.kickoff.core.soccer.team.external.ExternalTeamIdMappingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class StandingBatchService {
    private final SoccerApiFeign soccerApiFeign;
    private final ExternalTeamIdMappingRepository externalTeamIdMappingRepository;
    private final TeamStandingRepository teamStandingRepository;

    public void insertStanding(String season, Long matchDay, Long leagueId)
    {
        if (teamStandingRepository.existsBySeasonAndRoundAndLeagueId(season, matchDay, leagueId))
        {
            log.info("해당 시즌과 라운드의 순위 정보가 이미 존재합니다. 시즌={}, 라운드={}", season, matchDay);
            return ;
        }

        log.info("순위 정보 삽입 시작: 시즌={}, 라운드={}, 리그 = {}", season, matchDay, leagueId);

        try {
            StandingResponse standingResponse = soccerApiFeign.getStandings(season, matchDay);
            processStandingResponse(standingResponse, season, matchDay, leagueId);
        } catch (Exception e) {
            log.error("순위 정보를 가져오는 중 오류 발생: 시즌={}, 라운드={}", season, matchDay, e);
        }
    }

    private void processStandingResponse(StandingResponse standingResponse, String season, Long matchDay, Long leagueId)
    {
        for (Standings standing : standingResponse.standings())
        {
            if (!"TOTAL".equals(standing.type()))
            {
                continue; // 총합 순위만 처리
            }

            standing.table().forEach(table -> processTableEntry(table, season, matchDay, leagueId));
        }
    }

    private void processTableEntry(Standings.Table table, String season, Long matchDay, Long leagueId)
    {
        int externalTeamId = table.team().id();

        Optional<ExternalTeamIdMapping> externalTeamIdMappingOpt = externalTeamIdMappingRepository
                .findByExternalTeamId((long) externalTeamId);

        externalTeamIdMappingOpt.ifPresentOrElse(
                externalTeamIdMapping -> saveTeamStanding(table, season, matchDay, leagueId, externalTeamIdMapping.getTeamId()),
                () -> log.warn("외부 팀 ID 매핑을 찾을 수 없음: externalTeamId={}", externalTeamId)
        );
    }

    private void saveTeamStanding(Standings.Table table, String season, Long matchDay, Long leagueId, Long teamId)
    {
        TeamStanding teamStanding = TeamStanding.builder()
                .ranks(table.position())
                .leagueId(leagueId)
                .round(matchDay)
                .teamId(teamId)
                .draw(table.draw())
                .won(table.won())
                .goalsAgainst(table.goalsAgainst())
                .points(table.points())
                .goalsFor(table.goalsFor())
                .season(season)
                .lost(table.lost())
                .build();

        teamStandingRepository.save(teamStanding);
        log.info("순위 정보 저장 완료: 팀 ID={}, 시즌= {}, 라운드= {}", teamId, season, matchDay);
    }
}

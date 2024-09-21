package com.kickoff.batch.jobs.team.standing.service;

import com.kickoff.batch.config.feign.SoccerApiFeign;
import com.kickoff.batch.config.feign.api.temp.StandingResponse;
import com.kickoff.batch.config.feign.api.temp.Standings;
import com.kickoff.core.soccer.league.League;
import com.kickoff.core.soccer.league.LeagueRepository;
import com.kickoff.core.soccer.league.Season;
import com.kickoff.core.soccer.league.SeasonRepository;
import com.kickoff.core.soccer.league.game.LeagueGameRepository;
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
    private final LeagueRepository leagueRepository;
    private final LeagueGameRepository leagueGameRepository;
    private final SeasonRepository seasonRepository;

    public void insertStanding(String seasonYears, String competition)
    {
        Season findSeason = seasonRepository.findByYears(seasonYears).orElseThrow();
        League league = leagueRepository.findByLeagueNameAndSeason(competition, findSeason).orElseThrow();

        try {
            StandingResponse standingResponse = soccerApiFeign.getStandings(seasonYears, competition, null);
            int currentMatchDay = standingResponse.season().currentMatchday();

            for (long matchday = 1; matchday <= currentMatchDay; matchday++)
            {
                processStandingResponse(seasonYears, matchday, league, competition);
            }

        } catch (Exception e) {
            log.error("순위 정보를 가져오는 중 오류 발생: season={}, competition={}", seasonYears, competition, e);
        }
    }

    private void processStandingResponse(String season, Long matchDay, League league, String competition)
    {
        StandingResponse standingResponse = soccerApiFeign.getStandings(season, competition, matchDay);
        for (Standings standing : standingResponse.standings())
        {
            if (!"TOTAL".equals(standing.type()))
            {
                continue; // 총합 순위만 처리
            }

            standing.table().forEach(table -> processTableEntry(table, season, matchDay, league));
        }
    }

    private void processTableEntry(Standings.Table table, String season, Long matchDay, League league)
    {
        int externalTeamId = table.team().id();

        Optional<ExternalTeamIdMapping> externalTeamIdMappingOpt = externalTeamIdMappingRepository
                .findByExternalTeamIdAndSeason((long) externalTeamId, season);
        log.info("table: {}/{}/{}, season : {}, matchDay : {}", table.won(), table.draw(), table.lost(), season, matchDay);
        externalTeamIdMappingOpt.ifPresentOrElse(
                externalTeamIdMapping -> saveTeamStanding(table, season, matchDay, league, externalTeamIdMapping.getTeamId()),
                () -> log.warn("외부 팀 ID 매핑을 찾을 수 없음: externalTeamId={}", externalTeamId)
        );
    }

    private void saveTeamStanding(Standings.Table table, String season, Long matchDay, League league, Long teamId)
    {
        if (teamStandingRepository.existsByTeamIdAndSeasonAndRoundAndLeagueId(teamId, season, matchDay, league.getLeagueId()))
        {
            return;
        }

        TeamStanding teamStanding = teamStandingRepository.findByTeamIdAndSeasonAndRoundAndLeagueId(teamId, season, matchDay, league.getLeagueId()).orElse(
                TeamStanding.builder()
                        .ranks(table.position())
                        .leagueId(league.getLeagueId())
                        .round(matchDay)
                        .teamId(teamId)
                        .draw(table.draw())
                        .won(table.won())
                        .goalsAgainst(table.goalsAgainst())
                        .points(table.points())
                        .goalsFor(table.goalsFor())
                        .season(season)
                        .lost(table.lost())
                        .build()
        );

        teamStanding.update(TeamStanding.builder()
                .ranks(table.position())
                .leagueId(league.getLeagueId())
                .round(matchDay)
                .teamId(teamId)
                .draw(table.draw())
                .won(table.won())
                .goalsAgainst(table.goalsAgainst())
                .points(table.points())
                .goalsFor(table.goalsFor())
                .season(season)
                .lost(table.lost())
                .build());

        teamStandingRepository.save(teamStanding);
        log.info("순위 정보 저장 완료: 팀 ID={}, 시즌= {}, 라운드= {}", teamId, season, matchDay);
    }
}

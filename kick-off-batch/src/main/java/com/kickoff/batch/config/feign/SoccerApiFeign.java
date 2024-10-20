package com.kickoff.batch.config.feign;

import com.kickoff.batch.config.feign.api.CompetitionTeamsResponse;
import com.kickoff.batch.config.feign.api.LeaguePlayerRankResponse;
import com.kickoff.batch.config.feign.api.MatchResultResponse;
import com.kickoff.batch.config.feign.api.MatchesFeignResponse;
import com.kickoff.batch.config.feign.api.temp.Competitions;
import com.kickoff.batch.config.feign.api.temp.Match;
import com.kickoff.batch.config.feign.api.temp.StandingResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "soccerApi", url = "https://api.football-data.org", configuration = FeignCustomConfig.class)
public interface SoccerApiFeign {
    @GetMapping("/v4/competitions/{competition}/matches")
    MatchesFeignResponse getLatestClMatch(@PathVariable(value = "competition") String competition);
    /**
     * @param competition ex) PL
     * @param season 시즌 ex) 2023
     */
    @GetMapping("/v4/competitions/{competition}/teams")
    CompetitionTeamsResponse getCompetitionTeams(
            @PathVariable(value = "competition") String competition,
            @RequestParam(name = "season") String season);

    /**
     * 매치를 조회 한다.
     * @param competitions 대회 이름 ex : PL(프리미어리그), CL(챔피언스리그)
     * @param dateFrom 경기 시작 파라미터
     * @param dateTo 경기 끝 파라미터
     * @return 해당 날짜에 해당 되는 대회의 모든 경기 리스트
     */
    @GetMapping("/v4/matches")
    MatchResultResponse getMatchResultResponse(
            @RequestParam(name = "competitions") String competitions,
            @RequestParam(name = "dateFrom") String dateFrom,
            @RequestParam(name = "dateTo") String dateTo
    );


    @GetMapping("/v4/competitions/{competitions}/standings")
    StandingResponse getStandings(
            @RequestParam(name = "season") String season,
            @PathVariable(name = "competitions") String competitions,
            @RequestParam(required = false, name = "matchday") Long matchday
    );

    @GetMapping("/v4/competitions/{competitions}/scorers")
    LeaguePlayerRankResponse leagueRank(
            @PathVariable(name = "competitions") String competitions,
            @RequestParam(name = "season") String season);

    @GetMapping("/v4/matches/{matchId}")
    Match getCompetitionMatchResponse(
            @PathVariable(value = "matchId") Long matchId);

    @GetMapping("/v4/competitions/{competitions}")
    Competitions getCompetitionResponse(
            @PathVariable(value = "competitions") String competitions);

    @DeleteMapping("/v4/all-data")
    void deleteAllData();

}

package com.kickoff.batch.jobs.competition.service;

import com.kickoff.batch.config.feign.SoccerApiFeign;
import com.kickoff.batch.config.feign.api.CompetitionTeamsResponse;
import com.kickoff.core.common.National;
import com.kickoff.core.soccer.player.Contract;
import com.kickoff.core.soccer.player.Player;
import com.kickoff.core.soccer.player.PlayerPosition;
import com.kickoff.core.soccer.player.PlayerRepository;
import com.kickoff.core.soccer.team.TeamType;
import com.kickoff.core.soccer.team.league.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@RequiredArgsConstructor
@Service
public class DailyTeamSquadService {
    private final SoccerApiFeign soccerApiFeign;
    private final LeagueRepository leagueRepository;
    private final SeasonRepository seasonRepository;
    private final LeagueTeamRepository leagueTeamRepository;
    private final PlayerRepository playerRepository;

    public void insertTeamSquad(String year, String competition)
    {
        CompetitionTeamsResponse competitionTeams = soccerApiFeign.getCompetitionTeams(competition, year);
        Season season = seasonRepository.findByYears(year).orElse(Season.builder().years(year).build());
        seasonRepository.save(season);

        League league = League.builder()
                .leagueName(competition)
                .national(National.of(competition))
                .emblem(competitionTeams.competition().emblem())
                .season(season)
                .build();

        leagueRepository.save(league);

        for (CompetitionTeamsResponse.Team team : competitionTeams.teams())
        {
            LeagueTeam leagueTeam = LeagueTeam.builder()
                    .teamType(TeamType.of(competition))
                    .season(season)
                    .leagueTeamName(team.name())
                    .league(league)
                    .logo(team.crest())
                    .build();

            leagueTeamRepository.save(leagueTeam);

            for (CompetitionTeamsResponse.Squad squad : team.squad())
            {
                Player player = Player.builder()
                        .leagueTeam(leagueTeam)
                        .contract(new Contract(squad.contract().start(), squad.contract().until()))
                        .national(squad.nationality())
                        .position(convertPosition(squad.position()))
                        .playerName(squad.name())
                        .birth(squad.dateOfBirth())
                        .season(season)
                        .build();

                playerRepository.save(player);
            }
        }
    }

    private PlayerPosition convertPosition(String position)
    {
        return switch (position) {
            case "Goalkeeper" -> PlayerPosition.KEEPER;
            case "Defence" -> PlayerPosition.DEFENDER;
            case "Midfield" -> PlayerPosition.MID_FIELDER;
            case "Offence" -> PlayerPosition.FORWARD;
            default -> null;
        };
    }
}
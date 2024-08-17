package com.kickoff.batch.jobs.team.squad.service;

import com.kickoff.batch.config.feign.SoccerApiFeign;
import com.kickoff.batch.config.feign.api.CompetitionTeamsResponse;
import com.kickoff.batch.config.feign.api.temp.Squad;
import com.kickoff.batch.config.feign.api.temp.Team;
import com.kickoff.core.soccer.player.Contract;
import com.kickoff.core.soccer.player.Player;
import com.kickoff.core.soccer.player.PlayerPosition;
import com.kickoff.core.soccer.player.PlayerRepository;
import com.kickoff.core.soccer.team.external.ExternalTeamIdMapping;
import com.kickoff.core.soccer.team.external.ExternalTeamIdMappingRepository;
import com.kickoff.core.soccer.team.league.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class DailyTeamSquadService {
    private final SoccerApiFeign soccerApiFeign;
    private final LeagueRepository leagueRepository;
    private final SeasonRepository seasonRepository;
    private final LeagueTeamRepository leagueTeamRepository;
    private final PlayerRepository playerRepository;
    private final ExternalTeamIdMappingRepository externalTeamIdMappingRepository;

    public void insertTeamSquad(String year, String competition)
    {
        CompetitionTeamsResponse competitionTeams = soccerApiFeign.getCompetitionTeams(competition, year);
        Season season = seasonRepository.findByYears(year).orElse(Season.builder().years(year).build());
        seasonRepository.save(season);

        League league = leagueRepository.findByLeagueName("PL");

        for (Team team : competitionTeams.teams())
        {
            Optional<ExternalTeamIdMapping> byExternalTeamId = externalTeamIdMappingRepository.findByExternalTeamId((long) team.id());

            if (byExternalTeamId.isEmpty())
            {
                continue;
            }
            ExternalTeamIdMapping externalTeamIdMapping = byExternalTeamId.get();

            LeagueTeam leagueTeam = leagueTeamRepository.findById(externalTeamIdMapping.getTeamId()).orElseThrow();

            for (Squad squad : team.squad())
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
package com.kickoff.batch.jobs.team.squad.service;

import com.kickoff.batch.config.feign.SoccerApiFeign;
import com.kickoff.batch.config.feign.api.CompetitionTeamsResponse;
import com.kickoff.batch.config.feign.api.temp.Squad;
import com.kickoff.batch.config.feign.api.temp.Team;
import com.kickoff.core.soccer.player.Contract;
import com.kickoff.core.soccer.player.Player;
import com.kickoff.core.soccer.player.PlayerPosition;
import com.kickoff.core.soccer.player.PlayerRepository;
import com.kickoff.core.soccer.team.TeamType;
import com.kickoff.core.soccer.team.external.ExternalApiName;
import com.kickoff.core.soccer.team.external.ExternalTeamIdMapping;
import com.kickoff.core.soccer.team.external.ExternalTeamIdMappingRepository;
import com.kickoff.core.soccer.team.league.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
@Slf4j
public class DailyTeamSquadService {
    private final SoccerApiFeign soccerApiFeign;
    private final SeasonRepository seasonRepository;
    private final LeagueTeamRepository leagueTeamRepository;
    private final LeagueRepository leagueRepository;
    private final PlayerRepository playerRepository;
    private final ExternalTeamIdMappingRepository externalTeamIdMappingRepository;

    public void insertTeamSquad(String year, String competition)
    {
        CompetitionTeamsResponse competitionTeams = soccerApiFeign.getCompetitionTeams(competition, year);
        Season season = seasonRepository.findByYears(year).orElse(Season.builder().years(year).build());
        seasonRepository.save(season);

        Optional<League> byLeagueName = leagueRepository.findByLeagueName(competition);
        List<LeagueTeam> findLeagueTeam = leagueTeamRepository.findByLeagueName(competition);

        if (!findLeagueTeam.isEmpty())
        {
            leagueTeamRepository.deleteAll(findLeagueTeam);
        }

        if (byLeagueName.isEmpty())
        {
            throw new IllegalArgumentException("Not Founded League");
        }

        for (Team team : competitionTeams.teams())
        {
            LeagueTeam leagueTeam = leagueTeamRepository.findByLeagueTeamName(team.name()).orElse(
                    LeagueTeam
                        .builder()
                        .leagueTeamName(team.name())
                        .teamType(TeamType.LEAGUE)
                        .logo(team.crest())
                        .season(season).league(byLeagueName.get())
                    .build()
            );



            ExternalTeamIdMapping externalTeamIdMapping = ExternalTeamIdMapping.builder()
                    .externalTeamId((long) team.id())
                    .externalApiName(ExternalApiName.FOOT_BALL_API_V1)
                    .leagueTeamId(leagueTeam.getLeagueTeamId())
                    .build();

            externalTeamIdMappingRepository.save(externalTeamIdMapping);
            deleteIfExistsPlayers(season, leagueTeam);

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

    private void deleteIfExistsPlayers(Season season, LeagueTeam leagueTeam) {
        List<Player> players = playerRepository.findByLeagueTeamAndSeason(leagueTeam, season);

        if (players.isEmpty())
        {
            playerRepository.deleteAll(players);
        }
    }

    private PlayerPosition convertPosition(String position)
    {
        if (position == null)
        {
            return null;
        }
        return switch (position) {
            case "Goalkeeper" -> PlayerPosition.KEEPER;
            case "Defence" -> PlayerPosition.DEFENDER;
            case "Midfield" -> PlayerPosition.MID_FIELDER;
            case "Left-Back" -> PlayerPosition.LEFT_BACK;
            case "Right-Back" -> PlayerPosition.RIGHT_BACK;
            case "Centre-Back" -> PlayerPosition.CENTER_BACK;
            case "Offence" -> PlayerPosition.FORWARD;
            case "Centre-Forward" -> PlayerPosition.CENTER_FORWARD;
            case "Central Midfield" -> PlayerPosition.CENTRAL_MID_FIELDER;
            case "Right Winger" -> PlayerPosition.RIGHT_WINGER;
            case "Left Winger" -> PlayerPosition.LEFT_WINGER;
            case "Attacking Midfield" -> PlayerPosition.ATTACKING_MIDFIELD;
            default -> null;
        };
    }
}
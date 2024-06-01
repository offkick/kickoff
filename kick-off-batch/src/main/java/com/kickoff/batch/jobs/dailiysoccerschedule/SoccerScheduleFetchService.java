package com.kickoff.batch.jobs.dailiysoccerschedule;

import com.kickoff.batch.config.feign.SoccerApiFeign;
import com.kickoff.batch.config.feign.api.CompetitionTeamsResponse;
import com.kickoff.batch.config.feign.api.MatchesFeignResponse;
import com.kickoff.core.common.National;
import com.kickoff.core.soccer.player.Player;
import com.kickoff.core.soccer.player.PlayerPosition;
import com.kickoff.core.soccer.player.PlayerRepository;
import com.kickoff.core.soccer.team.Score;
import com.kickoff.core.soccer.team.TeamType;
import com.kickoff.core.soccer.team.league.*;
import com.kickoff.core.soccer.team.league.game.LeagueGame;
import com.kickoff.core.soccer.team.league.game.LeagueGameRepository;
import com.kickoff.core.soccer.team.league.game.LeagueGameStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class SoccerScheduleFetchService {
    private static final String LEAGUE = "CL";

    private final SoccerApiFeign soccerApiFeign;
    private final LeagueRepository leagueRepository;
    private final LeagueGameRepository leagueGameRepository;
    private final SeasonRepository seasonRepository;
    private final LeagueTeamRepository leagueTeamRepository;
    private final PlayerRepository playerRepository;

    public void competitionInsert(String target)
    {
        CompetitionTeamsResponse competitionTeams = soccerApiFeign.getCompetitionTeams(target,"2023");

        Season season = Season.builder()
                .season("2023-2024")
                .build();

        seasonRepository.save(season);

        League league = League.builder()
                .leagueName(target)
                .national(National.ENGLAND)
                .season(season)
                .build();

        leagueRepository.save(league);

        for (CompetitionTeamsResponse.Team team : competitionTeams.teams())
        {
            LeagueTeam leagueTeam = LeagueTeam.builder()
                    .teamType(TeamType.LEAGUE)
                    .season(season)
                    .leagueTeamName(team.name())
                    .league(league)
                    .build();

            leagueTeamRepository.save(leagueTeam);

            for (CompetitionTeamsResponse.Squad squad : team.squad())
            {
                Player player = Player.builder()
                        .leagueTeam(leagueTeam)
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

    public void createCLSchedules()
    {
        MatchesFeignResponse latestClMatch = soccerApiFeign.getLatestClMatch();
        League champianceLeague = League.builder()
                .leagueName(LEAGUE)
                .build();

        leagueRepository.save(champianceLeague);

        for (MatchesFeignResponse.Match match : latestClMatch.matches())
        {
            LeagueTeam homeTeam = leagueTeamRepository.findByLeagueTeamName(match.homeTeam().name())
                    .orElse(LeagueTeam.builder().teamType(TeamType.LEAGUE).league(champianceLeague).leagueTeamName(match.homeTeam().name()).build());

            LeagueTeam awayTeam = leagueTeamRepository.findByLeagueTeamName(match.awayTeam().name())
                    .orElse(LeagueTeam.builder().teamType(TeamType.LEAGUE).league(champianceLeague).leagueTeamName(match.awayTeam().name()).build());

            leagueTeamRepository.save(homeTeam);
            leagueTeamRepository.save(awayTeam);

            Score score = Score.builder()
                    .awayScore(match.score().fullTime() != null ? match.score().fullTime().away() : null)
                    .homeScore(match.score().fullTime() != null ? match.score().fullTime().home() : null)
                    .build();


            LeagueGame leagueGame = LeagueGame.builder()
                    .leagueGameStatus(LeagueGameStatus.GAMING)
                    .gameDate(match.utcDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                    .home(homeTeam)
                    .away(awayTeam)
                    .count(match.matchday())
                    .score(score)
                    .homePlayers(List.of())
                    .awayPlayers(List.of())
                    .build();

            leagueGameRepository.save(leagueGame);
        }
    }
}

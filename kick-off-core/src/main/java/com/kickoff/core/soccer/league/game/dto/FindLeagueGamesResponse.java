package com.kickoff.core.soccer.league.game.dto;

import com.kickoff.core.soccer.league.LeagueTeam;
import com.kickoff.core.soccer.league.game.LeagueGame;
import com.kickoff.core.soccer.league.game.LeagueGameStatus;
import com.kickoff.core.soccer.league.game.player.LeagueGamePlayerStatus;
import com.kickoff.core.soccer.player.PlayerPosition;
import com.kickoff.core.soccer.team.Score;
import com.kickoff.core.soccer.team.TeamType;
import lombok.Builder;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
public record FindLeagueGamesResponse(List<FindLeagueGames> findLeagueGames, long totalElements, int totalPages) {

    public static FindLeagueGamesResponse of(Page<LeagueGame> leagueGames)
    {
        List<FindLeagueGames> findLeagueGames = leagueGames.stream()
                .map(FindLeagueGames::of)
                .collect(Collectors.toList());

        return FindLeagueGamesResponse.builder()
                .findLeagueGames(findLeagueGames)
                .totalElements(leagueGames.getTotalElements())
                .totalPages(leagueGames.getTotalPages())
                .build();
    }

    public record LeagueTeamDTO(
            Long leagueTeamId,
            String leagueTeamName,
            TeamType teamType,
            String league,
            String season,
            String logo
    ) {
        public static LeagueTeamDTO of(LeagueTeam leagueTeam)
        {
            return new LeagueTeamDTO(
                    leagueTeam.getLeagueTeamId(),
                    leagueTeam.getLeagueTeamName(),
                    leagueTeam.getTeamType(),
                    leagueTeam.getLeague().getLeagueName(),
                    leagueTeam.getSeason().getYears(),
                    leagueTeam.getLogo()
            );
        }
    }

    public record PlayerDTO(
            Long playerId,
            String national,
            String playerName,
            PlayerPosition position,
            LeagueTeamDTO leagueTeam,
            String birth,
            String season
    ) {}

    public record LeagueGamePlayerDTO(
            Long LeagueGamePlayerId,
            LeagueGamePlayerStatus status,
            int playedTime,
            int subTime,
            PlayerPosition position,
            PlayerDTO player
    ) {}

    public record FindLeagueGames(
            Long leagueGameId,
            LocalDateTime gameDate,
            int matchDay,
            String away,
            String home,
            Score score,
            LeagueGameStatus leagueGameStatus,
            String venue,
            List<LeagueGamePlayerDTO> homePlayers,
            List<LeagueGamePlayerDTO> awayPlayers
    ) {
        public static FindLeagueGames of(LeagueGame leagueGame)
        {
            return new FindLeagueGames(
                    leagueGame.getLeagueGameId(),
                    leagueGame.getGameDate(),
                    leagueGame.getMatchDay(),
                    leagueGame.getAway().getLeagueTeamName(),
                    leagueGame.getHome().getLeagueTeamName(),
                    leagueGame.getScore() == null ? null : leagueGame.getScore(),
                    leagueGame.getLeagueGameStatus(),
                    leagueGame.getVenue(),
                    null,
                    null
            );
        }
    }
}

package com.kickoff.api.controller.team.league.dto;

import com.kickoff.core.soccer.player.PlayerPosition;
import com.kickoff.core.soccer.team.league.game.LeagueGameStatus;
import com.kickoff.core.soccer.team.league.service.dto.LeagueGameDTO;

import java.time.LocalDateTime;
import java.util.List;

public record FindLeagueGamePlayerResponse(
        LeagueGamePlayerResponse response
) {
    public static FindLeagueGamePlayerResponse of(LeagueGameDTO leagueGame)
    {
        return new FindLeagueGamePlayerResponse(LeagueGamePlayerResponse.of(leagueGame));
    }

    public record GamePlayer(
            String name,
            int playedTime,
            int subTime,
            PlayerPosition position
    ) {}

    public record LeagueGamePlayerResponse(
            LocalDateTime gameDate,
            int count,
            String away,
            String home,
            String homeScore,
            String awayScore,
            LeagueGameStatus leagueGameStatus,
            String venue,
            List<GamePlayer> homePlayers,
            List<GamePlayer> awayPlayers
    ) {
        public static LeagueGamePlayerResponse of(LeagueGameDTO leagueGame) {
            return new LeagueGamePlayerResponse(
                    leagueGame.gameDate(),
                    leagueGame.count(),
                    leagueGame.away().leagueTeamName(),
                    leagueGame.home().leagueTeamName(),
                    leagueGame.score().homeScore(),
                    leagueGame.score().awayScore(),
                    leagueGame.leagueGameStatus(),
                    leagueGame.venue(),
                    null,
                    null
            );
        }
    }
}

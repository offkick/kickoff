package com.kickoff.core.soccer.team.league.game.dto;

import com.kickoff.core.soccer.team.Score;
import com.kickoff.core.soccer.team.league.LeagueTeam;
import com.kickoff.core.soccer.team.league.Season;
import com.kickoff.core.soccer.team.league.game.LeagueGame;
import com.kickoff.core.soccer.team.league.game.LeagueGameStatus;
import com.kickoff.core.soccer.team.league.game.player.LeagueGamePlayer;
import lombok.Builder;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
public record FindLeagueGamesResponse(
        List<FindLeagueGames> findLeagueGames,
        int totalPages,
        long totalElements
) {
    public static FindLeagueGamesResponse of(Page<LeagueGame> leagueGames)
    {
        List<FindLeagueGames> findLeagueGames = leagueGames.stream()
                .map(FindLeagueGames::from)
                .collect(Collectors.toList());
        return FindLeagueGamesResponse.builder()
                .findLeagueGames(findLeagueGames)
                .totalElements(leagueGames.getTotalElements())
                .totalPages(leagueGames.getTotalPages())
                .build();

    }
    public record FindLeagueGames(
            Long leagueGameId,
            LocalDateTime gameDate,
            int count,
            LeagueTeam away,
            LeagueTeam home,
            Score score,
            LeagueGameStatus leagueGameStatus,
            Season season,
            List<LeagueGamePlayer> homePlayers,
            List<LeagueGamePlayer> awayPlayers
    ){
        public static FindLeagueGames from(LeagueGame leagueGame)
        {
            return new FindLeagueGames(
                    leagueGame.getLeagueGameId(),
                    leagueGame.getGameDate(),
                    leagueGame.getCount(),
                    leagueGame.getAway(),
                    leagueGame.getHome(),
                    leagueGame.getScore(),
                    leagueGame.getLeagueGameStatus(),
                    leagueGame.getSeason(),
                    leagueGame.getHomePlayers(),
                    leagueGame.getAwayPlayers()
            );
        }
    }
}

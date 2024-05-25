package com.kickoff.admin.service;

import com.kickoff.admin.service.dto.CreatePlayerAdminRequest;
import com.kickoff.admin.service.dto.FindLeagueGameResponses;
import com.kickoff.admin.service.dto.FindLeagueResponses;
import com.kickoff.admin.service.dto.FindPlayerResponses;
import com.kickoff.core.soccer.player.Player;
import com.kickoff.core.soccer.player.dto.FindPlayerResponse;
import com.kickoff.core.soccer.player.dto.PlayerSearchCondition;
import com.kickoff.core.soccer.player.service.PlayerService;
import com.kickoff.core.soccer.player.service.dto.CreatePlayerRequest;
import com.kickoff.core.soccer.team.league.League;
import com.kickoff.core.soccer.team.league.LeagueRepository;
import com.kickoff.core.soccer.team.league.game.LeagueGameRepositoryImpl;
import com.kickoff.core.soccer.team.league.game.dto.FindLeagueGameResponse;
import com.kickoff.core.soccer.team.league.game.dto.GameSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PlayerAdminService {
    private final PlayerService playerService;
    private final LeagueRepository leagueRepository;
    private final LeagueGameRepositoryImpl leagueGameRepositoryImpl;

    public Long save(CreatePlayerAdminRequest request)
    {
        CreatePlayerRequest createPlayerRequest = new CreatePlayerRequest(
                request.national(),
                request.playerName(),
                request.playerPosition(),
                request.leagueTeamId()
        );
        return playerService.save(createPlayerRequest);
    }

    public Player findPlayer(Long id){
        return playerService.findPlayerById(id);
    }

    public List<FindLeagueResponses> findAllLeagues() {
        List<League> leagues = leagueRepository.findAll();
        return leagues.stream()
                .map(FindLeagueResponses::from)
                .collect(Collectors.toList());
    }

    public Page<FindLeagueGameResponses> findLeagueGames(GameSearchCondition gameSearchCondition, Pageable pageable){
        Page<FindLeagueGameResponse> responses = leagueGameRepositoryImpl.searchGame(gameSearchCondition, pageable);

        List<FindLeagueGameResponses> convertedResponses = responses.getContent().stream()
                .map(response -> new FindLeagueGameResponses(response.leagueGameId(),
                        response.gameDate(),
                        response.count(),
                        response.away(),
                        response.home(),
                        response.score(),
                        response.leagueGameStatus(),
                        response.season(),
                        response.homePlayers(),
                        response.awayPlayers()))
                .collect(Collectors.toList());

        return new PageImpl<>(convertedResponses, pageable, responses.getTotalElements());
    }

    public Page<FindPlayerResponses> findPlayers(PlayerSearchCondition condition, Pageable pageable){
        Page<FindPlayerResponse> findPlayerResponses = playerService.searchPlayers(condition, pageable);
        List<FindPlayerResponses> playerList = findPlayerResponses.getContent().stream()
                .map(p -> new FindPlayerResponses(
                        p.playerId(),
                        p.national(),
                        p.playerName(),
                        p.position(),
                        p.leagueTeam()
                )).collect(Collectors.toList());


        return new PageImpl<>(playerList,pageable,findPlayerResponses.getTotalElements());
    }

}

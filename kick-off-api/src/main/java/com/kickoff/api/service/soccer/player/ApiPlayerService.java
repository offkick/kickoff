package com.kickoff.api.service.soccer.player;

import com.kickoff.api.service.soccer.player.dto.CreatePlayerServiceRequest;
import com.kickoff.core.soccer.player.Player;
import com.kickoff.core.soccer.player.PlayerRepository;
import com.kickoff.core.soccer.player.dto.FindPlayerResponse;
import com.kickoff.core.soccer.player.service.PlayerService;
import com.kickoff.core.soccer.team.league.LeagueTeam;
import com.kickoff.core.soccer.team.league.LeagueTeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ApiPlayerService {
    private final PlayerRepository playerRepository;
    private final LeagueTeamRepository leagueTeamRepository;
    private final PlayerService playerService;

    public Long createPlayer(CreatePlayerServiceRequest request)
    {
        LeagueTeam leagueTeam = leagueTeamRepository.findById(request.leagueTeamId())
                .orElseThrow(()->new IllegalArgumentException());

        Player player = Player.builder()
                .playerName(request.playerName())
                .national(request.national())
                .leagueTeam(leagueTeam)
                .position(request.playerPosition())
                .build();

        return playerRepository.save(player).getPlayerId();
    }
//    public FindPlayerResponse findPlayerResponse(Long playerId)
//    {
//        FindPlayerResponse players = playerService.findPlayers(playerId);
//        return players;
//
//    }







}

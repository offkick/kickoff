package com.kickoff.admin.service;

import com.kickoff.admin.service.dto.CreatePlayerAdminRequest;
import com.kickoff.domain.soccer.player.Player;
import com.kickoff.domain.soccer.player.service.PlayerService;
import com.kickoff.domain.soccer.player.service.dto.CreatePlayerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PlayerAdminService {
    private final PlayerService playerService;

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

    public List<Player> findAllPlayers(){
        return playerService.findPlayers();
    }

    public Player findPlayer(Long id){
        return playerService.findPlayerById(id);
    }
    public List<Player> findPlayer(String playerName, String national, Long leagueTeamId){
        return playerService.findPlayer(playerName, national, leagueTeamId);
    }
}

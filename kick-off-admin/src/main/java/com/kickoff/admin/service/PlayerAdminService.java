package com.kickoff.admin.service;

import com.kickoff.admin.service.dto.CreatePlayerAdminRequest;
import com.kickoff.domain.soccer.player.dto.CreatePlayerRequest;
import com.kickoff.domain.soccer.player.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}

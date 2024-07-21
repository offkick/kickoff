package com.kickoff.api.service.soccer.player;

import com.kickoff.api.controller.soccer.player.dto.FindPlayerApiResponse;
import com.kickoff.core.soccer.player.dto.PlayerDTO;
import com.kickoff.core.soccer.player.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ApiPlayerService {
    private final PlayerService playerService;

    public FindPlayerApiResponse findPlayers(Long playerId)
    {
        PlayerDTO players = playerService.findPlayers(playerId);
        return FindPlayerApiResponse.from(players);
    }
}

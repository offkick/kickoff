package com.kickoff.admin.service;

import com.kickoff.domain.soccer.player.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PlayerAdminService {
    private final PlayerService playerService;

    public void save()
    {
        playerService.save();
    }
}

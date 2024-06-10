package com.kickoff.api.controller.soccer.player;

import com.kickoff.api.controller.soccer.player.dto.FindPlayerResponseDto;
import com.kickoff.api.service.soccer.player.ApiPlayerService;
import com.kickoff.core.soccer.player.dto.FindPlayerResponse;
import com.kickoff.core.soccer.player.service.PlayerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "선수 컨트롤러", description = "선수 관련 리소스 검색/변경")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/player")
public class PlayerController {
    private final ApiPlayerService apiPlayerService;

    @GetMapping("/{playerId}")
    public FindPlayerResponseDto findPlayer(@PathVariable(value = "playerId") Long playerId)
    {
        return apiPlayerService.findPlayers(playerId);
    }

}

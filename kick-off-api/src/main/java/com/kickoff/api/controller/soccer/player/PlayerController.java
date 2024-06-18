package com.kickoff.api.controller.soccer.player;

import com.kickoff.api.controller.soccer.player.dto.FindPlayerResponseDto;
import com.kickoff.api.service.soccer.player.ApiPlayerService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "선수 컨트롤러", description = "선수 관련 검색 / 변경")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/player")
public class PlayerController {
    private final ApiPlayerService apiPlayerService;

    @Parameters({
            @Parameter(name = "playerId", description = "선수id"),
    })
    @GetMapping("/{playerId}")
    public FindPlayerResponseDto findPlayer(@PathVariable(value = "playerId") Long playerId)
    {
        return apiPlayerService.findPlayers(playerId);
    }
}

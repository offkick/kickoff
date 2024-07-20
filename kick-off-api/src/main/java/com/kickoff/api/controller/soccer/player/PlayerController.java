package com.kickoff.api.controller.soccer.player;

import com.kickoff.api.controller.soccer.player.dto.FindPlayerResponse;
import com.kickoff.api.service.soccer.player.ApiPlayerService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "선수 컨트롤러", description = "선수 관련 검색 / 변경")
@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/player")
public class PlayerController {
    private final ApiPlayerService apiPlayerService;

    @Parameters({
            @Parameter(name = "playerId", description = "선수id"),
    })
    @GetMapping("/{playerId}")
    public FindPlayerResponse findPlayer(@PathVariable(value = "playerId") Long playerId)
    {
        log.debug("출력");
        return apiPlayerService.findPlayers(playerId);
    }
}

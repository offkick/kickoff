package com.kickoff.api.controller.soccer.player;

import com.kickoff.api.controller.soccer.player.dto.AllPlayerResponse;
import com.kickoff.api.controller.soccer.player.dto.FindPlayerApiResponse;
import com.kickoff.api.controller.soccer.player.dto.PlayerAddCommentRequest;
import com.kickoff.api.controller.soccer.player.dto.PlayerCommentResponse;
import com.kickoff.api.service.soccer.player.ApiPlayerService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@Tag(name = "선수 컨트롤러", description = "선수 관련 검색 / 변경")
@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/player")
public class PlayerController {
    private final ApiPlayerService apiPlayerService;

    @Parameters({@Parameter(name = "playerId", description = "선수id")})
    @GetMapping("/search/{playerId}")
    public FindPlayerApiResponse findPlayer(@PathVariable(value = "playerId") Long playerId)
    {
        return apiPlayerService.findPlayers(playerId);
    }

    @GetMapping("/search")
    public AllPlayerResponse findAllPlayer()
    {
        return apiPlayerService.findPlayers();
    }


    @PostMapping("/comment/{playerId}")
    public void addComment(@PathVariable Long playerId, @RequestBody PlayerAddCommentRequest request)
    {
        apiPlayerService.addComment(playerId, request);
    }

    @GetMapping("/comment/search/{playerId}")
    public PlayerCommentResponse findPlayerComments(@PathVariable Long playerId)
    {
        return apiPlayerService.findPlayerComments(playerId);
    }
}

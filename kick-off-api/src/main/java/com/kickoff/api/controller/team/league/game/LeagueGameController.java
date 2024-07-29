package com.kickoff.api.controller.team.league.game;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "리그 게임 컨트롤러", description = "리그 게임 관련 리소스 변경")
@Slf4j
@RequestMapping("/league-game")
@RequiredArgsConstructor
@RestController
public class LeagueGameController {

}

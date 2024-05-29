package com.kickoff.api.controller;

import com.kickoff.api.controller.team.league.game.LeagueGameController;
import com.kickoff.api.service.soccer.team.league.dto.FindLeagueGameResponseDto;
import com.kickoff.api.service.soccer.team.league.game.ApiLeagueGameFindService;
import com.kickoff.core.soccer.team.Score;
import com.kickoff.core.soccer.team.TeamType;
import com.kickoff.core.soccer.team.league.League;
import com.kickoff.core.soccer.team.league.LeagueTeam;
import com.kickoff.core.soccer.team.league.Season;
import com.kickoff.core.soccer.team.league.game.LeagueGameStatus;
import com.kickoff.core.soccer.team.league.game.dto.GameSearchCondition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LeagueGameController.class)
public class LeagueGameControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ApiLeagueGameFindService apiLeagueService;

    @Test
    public void testSearchGames() throws Exception {
        LeagueTeam leagueTeam = new LeagueTeam(1L,"mancity", TeamType.LEAGUE,null);
        LeagueTeam leagueTeam1 = new LeagueTeam(2L,"mu", TeamType.LEAGUE,null);

        Score score = new Score(1L,2,3);
        Season season = new Season(1L,"22-23");
        // 테스트용 데이터 생성
        FindLeagueGameResponseDto game1 = new FindLeagueGameResponseDto(
                1L,
                LocalDateTime.now(),
                3,
                leagueTeam,
                leagueTeam1,
                score,
                LeagueGameStatus.GAMING,
                season,
                null,
                null
                );
        FindLeagueGameResponseDto game2 = new FindLeagueGameResponseDto(2L,
                LocalDateTime.now(),
                2,
                leagueTeam1,
                leagueTeam,
                score,
                LeagueGameStatus.GAMING,
                season,
                null,
                null
        );
        List<FindLeagueGameResponseDto> games = Arrays.asList(game1, game2);
        Page<FindLeagueGameResponseDto> page = new PageImpl<>(games);

        // Mock 객체 설정
        when(apiLeagueService.findLeagueGames(any(GameSearchCondition.class), any(Pageable.class))).thenReturn(page);

        // GET 요청 실행 및 검증
        mockMvc.perform(get("/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(2));

        // apiLeagueService.findLeagueGames 메서드가 올바르게 호출되었는지 검증
//        verify(apiLeagueService, times(1)).findLeagueGames(any(GameSearchCondition.class), any(Pageable.class));
    }
}

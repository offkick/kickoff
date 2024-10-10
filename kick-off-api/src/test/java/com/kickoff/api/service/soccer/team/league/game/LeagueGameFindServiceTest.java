//package com.kickoff.api.service.soccer.team.league.game;
//
//import com.kickoff.core.common.National;
//import com.kickoff.core.soccer.game.LeagueGameQuerydslRepository;
//import com.kickoff.core.soccer.game.LeagueGameStatus;
//import com.kickoff.core.soccer.league.service.LeagueGameService;
//import com.kickoff.core.soccer.league.service.dto.*;
//import com.kickoff.core.soccer.team.TeamType;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class LeagueGameFindServiceTest {
//
//    @Autowired
//    LeagueGameService leagueGameService;
//
//    @Autowired
//    LeagueGameQuerydslRepository leagueGameQuerydslRepository;
//
//    @Test
//    void findByLeagueGameId() {
//        LeagueDTO leagueDTO = new LeagueDTO(
//                1L,
//                "EPL",
//                National.ENGLAND,
//                null,
//                "1",
//                "2024",
//                1L
//
//        );
//        LeagueTeamDTO leagueTeamDTO = new LeagueTeamDTO(
//                1L,
//                "토트넘",
//                TeamType.LEAGUE,
//                leagueDTO,
//                null
//
//        );
//        LeagueTeamDTO leagueTeamDTO2 = new LeagueTeamDTO(
//                2L,
//                "맨시티",
//                TeamType.LEAGUE,
//                leagueDTO,
//                null
//
//        );
//        ScoreDTO scoreDTO = new ScoreDTO(
//                "2","3"
//        );
//        LeagueGamePlayerDTO leagueGamePlayerDTO = new LeagueGamePlayerDTO(
//              1L,
//              "forward",
//              "1",
//              "손",
//              null
//        );
//        LeagueGamePlayerDTO leagueGamePlayerDTO2 = new LeagueGamePlayerDTO(
//                2L,
//                "forward",
//                "1",
//                "손",
//                null
//        );
//        LeagueGamePlayerDTO leagueGamePlayerDTO3 = new LeagueGamePlayerDTO(
//                3L,
//                "forward",
//                "1",
//                "손",
//                null
//        );
//        List<LeagueGamePlayerDTO> leagueGamePlayerDTOS = new ArrayList<>();
//        leagueGamePlayerDTOS.add(leagueGamePlayerDTO);
//        leagueGamePlayerDTOS.add(leagueGamePlayerDTO2);
//        leagueGamePlayerDTOS.add(leagueGamePlayerDTO3);
//
//        LeagueGamePlayerDTO leagueGamePlayerDTO4 = new LeagueGamePlayerDTO(
//                4L,
//                "forward",
//                "1",
//                "손",
//                null
//        );
//        LeagueGamePlayerDTO leagueGamePlayerDTO5 = new LeagueGamePlayerDTO(
//                5L,
//                "forward",
//                "1",
//                "손",
//                null
//        );
//        LeagueGamePlayerDTO leagueGamePlayerDTO6 = new LeagueGamePlayerDTO(
//                6L,
//                "forward",
//                "1",
//                "손",
//                null
//        );
//
//        List<LeagueGamePlayerDTO> leagueGamePlayerDTOS2 = new ArrayList<>();
//        leagueGamePlayerDTOS2.add(leagueGamePlayerDTO4);
//        leagueGamePlayerDTOS2.add(leagueGamePlayerDTO5);
//        leagueGamePlayerDTOS2.add(leagueGamePlayerDTO6);
//
//        LeagueGameDTO.SubstitutionInfo substitutionInfo = new LeagueGameDTO.SubstitutionInfo(
//                3,
//                1L,
//                "손흥민",
//                2L,
//                "손",
//                null
//        );
//
//        LeagueGameDTO.SubstitutionInfo substitutionInfo2 = new LeagueGameDTO.SubstitutionInfo(
//                14,
//                2L,
//                "손흥민",
//                3L,
//                "손",
//                null
//        );
//        List<LeagueGameDTO.SubstitutionInfo> substitutionInfos = new ArrayList<>();
//        substitutionInfos.add(substitutionInfo);
//        substitutionInfos.add(substitutionInfo2);
//        LeagueGameDTO leagueGameDTO6 = new LeagueGameDTO(
//                1L,
//                LocalDateTime.now(),
//                1,
//                leagueTeamDTO,
//                leagueTeamDTO2,
//                scoreDTO, null,
//                null,
//                null, null, null,
//                leagueGamePlayerDTOS2,
//                leagueGamePlayerDTOS,
//                null,
//                substitutionInfos,
//                null,null,null,null,null
//        );
//        when
//    }
//}
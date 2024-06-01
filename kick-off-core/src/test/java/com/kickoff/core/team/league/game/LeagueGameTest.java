//package com.kickoff.core.team.league.game;
//
//import com.kickoff.core.common.National;
//import com.kickoff.core.soccer.player.Player;
//import com.kickoff.core.soccer.player.PlayerPosition;
//import com.kickoff.core.soccer.team.Score;
//import com.kickoff.core.soccer.team.TeamType;
//import com.kickoff.core.soccer.team.league.League;
//import com.kickoff.core.soccer.team.league.LeagueTeam;
//import com.kickoff.core.soccer.team.league.Season;
//import com.kickoff.core.soccer.team.league.game.LeagueGame;
//import com.kickoff.core.soccer.team.league.game.LeagueGameStatus;
//import com.kickoff.core.soccer.team.league.game.player.LeagueGamePlayer;
//import com.kickoff.core.soccer.team.league.game.player.LeagueGamePlayerStatus;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//public class LeagueGameTest {
//
//    private static LeagueGamePlayer createLeagueGamePlayer(
//            Player player,
//            PlayerPosition position
//    ) {
//        return LeagueGamePlayer.builder()
//                .playedTime(90)
//                .status(LeagueGamePlayerStatus.STARTING)
//                .position(position)
//                .player(player)
//                .build();
//    }
//
//    private static Player createPlayer(String name, LeagueTeam leagueTeam, PlayerPosition position)
//    {
//        return Player.builder()
//                .playerName(name)
//                .leagueTeam(leagueTeam)
//                .position(position)
//                .build();
//    }
//
//
//    @DisplayName("모든 포지션 없음")
//    @Test
//    public void noAllPosition(){
//        League league = League.builder()
//                        .leagueName("k-league")
//                        .tier("K리그1")
//                        .national(National.KOREA)
//                        .build();
//
//        LeagueTeam homeTeam = LeagueTeam.builder()
//                .league(league)
//                        .leagueTeamName("mancity")
//                        .teamType(TeamType.LEAGUE)
//                        .build();
//
//        LeagueTeam awayTeam = LeagueTeam.builder()
//                .league(league)
//                .leagueTeamName("mancity")
//                .teamType(TeamType.LEAGUE)
//                .build();
//        Score score = Score.builder()
//                        .homeScore(1)
//                        .awayScore(3)
//                        .build();
//        Season season = Season.builder()
//                        .season("22-23")
//                        .build();
//        String [] playerNames = new String[]{
//                "kdb",
//                "ederson",
//                "dias",
//                "sonny",
//                "royal",
//                "maddison",
//                "vicario",
//                "이강인",
//                "김민재",
//                "황희찬",
//                "조현우"
//        };
//
//        PlayerPosition [] playerPositions = new PlayerPosition[]{
//                PlayerPosition.MID_FIELDER,
//                PlayerPosition.MID_FIELDER,
//                PlayerPosition.MID_FIELDER,
//                PlayerPosition.MID_FIELDER,
//                PlayerPosition.MID_FIELDER,
//                PlayerPosition.MID_FIELDER,
//                PlayerPosition.MID_FIELDER,
//                PlayerPosition.MID_FIELDER,
//                PlayerPosition.DEFENDER,
//                PlayerPosition.KEEPER,
//                PlayerPosition.KEEPER,
//        };
//
//        List<Player> homePlayers = new ArrayList<>();
//        List<Player> awayPlayers = new ArrayList<>();
//        List<LeagueGamePlayer> homeLeagueGamePlayers = new ArrayList<>();
//        List<LeagueGamePlayer> awayLeagueGamePlayers = new ArrayList<>();
//
//        for (int i = 0; i < playerNames.length; i++)
//        {
//            Player homePlayer = createPlayer(playerNames[i], homeTeam, playerPositions[i]);
//            Player awayPlayer = createPlayer(playerNames[i], awayTeam, playerPositions[i]);
//            homePlayers.add(homePlayer);
//            awayPlayers.add(awayPlayer);
//            homeLeagueGamePlayers.add(createLeagueGamePlayer(homePlayer, homePlayer.getPosition()));
//            awayLeagueGamePlayers.add(createLeagueGamePlayer(awayPlayer, awayPlayer.getPosition()));
//        }
//
//        Assertions.assertThatThrownBy(() -> LeagueGame.builder()
//                        .leagueGameStatus(LeagueGameStatus.GAMING)
//                        .gameDate(LocalDate.of(2024,11,24).atStartOfDay())
//                        .count(2)
//                        .away(awayTeam)
//                        .home(homeTeam)
//                        .score(score)
//                        .season(season)
//                        .homePlayers(homeLeagueGamePlayers)
//                        .awayPlayers(awayLeagueGamePlayers)
//                        .build())
//                .hasMessage("모든 포지션 없음")
//                .isInstanceOf(IllegalArgumentException.class);
//    }
//
//    @DisplayName("출전 선수 11명 안됨")
//    @Test
//    public void noPlayerEleven(){
//        LeagueTeam homeTeam = LeagueTeam.builder()
//                .leagueTeamName("mancity")
//                .teamType(TeamType.LEAGUE)
//                .build();
//
//        LeagueTeam awayTeam = LeagueTeam.builder()
//                .leagueTeamName("mancity")
//                .teamType(TeamType.LEAGUE)
//                .build();
//        Score score = Score.builder()
//                .homeScore(1)
//                .awayScore(3)
//                .build();
//        Season season = Season.builder()
//                .season("22-23")
//                .build();
//        String [] playerNames = new String[]{
//                "kdb",
//                "ederson",
//                "dias",
//                "sonny",
//                "royal",
//                "maddison",
//                "vicario",
//                "이강인",
//                "김민재",
//                "조현우"
//        };
//
//        PlayerPosition [] playerPositions = new PlayerPosition[]{
//                PlayerPosition.MID_FIELDER,
//                PlayerPosition.MID_FIELDER,
//                PlayerPosition.MID_FIELDER,
//                PlayerPosition.MID_FIELDER,
//                PlayerPosition.MID_FIELDER,
//                PlayerPosition.MID_FIELDER,
//                PlayerPosition.MID_FIELDER,
//                PlayerPosition.MID_FIELDER,
//                PlayerPosition.DEFENDER,
//                PlayerPosition.KEEPER,
//        };
//
//        List<Player> homePlayers = new ArrayList<>();
//        List<Player> awayPlayers = new ArrayList<>();
//        List<LeagueGamePlayer> homeLeagueGamePlayers = new ArrayList<>();
//        List<LeagueGamePlayer> awayLeagueGamePlayers = new ArrayList<>();
//
//        for (int i = 0; i < playerNames.length; i++)
//        {
//            Player homePlayer = createPlayer(playerNames[i], homeTeam, playerPositions[i]);
//            Player awayPlayer = createPlayer(playerNames[i], awayTeam, playerPositions[i]);
//            homePlayers.add(homePlayer);
//            awayPlayers.add(awayPlayer);
//            homeLeagueGamePlayers.add(createLeagueGamePlayer(homePlayer, homePlayer.getPosition()));
//            awayLeagueGamePlayers.add(createLeagueGamePlayer(awayPlayer, awayPlayer.getPosition()));
//        }
//
//        Assertions.assertThatThrownBy(() -> LeagueGame.builder()
//                        .leagueGameStatus(LeagueGameStatus.GAMING)
//                        .gameDate(LocalDate.of(2024,11,24).atStartOfDay())
//                        .count(2)
//                        .away(awayTeam)
//                        .home(homeTeam)
//                        .score(score)
//                        .season(season)
//                        .homePlayers(homeLeagueGamePlayers)
//                        .awayPlayers(awayLeagueGamePlayers)
//                        .build())
//                .hasMessage("출전 선수 11명 안됨")
//                .isInstanceOf(IllegalArgumentException.class);
//    }
//
//    @DisplayName("키퍼가 1명 아님")
//    @Test
//    public void noKeeperOne(){
//        LeagueTeam homeTeam = LeagueTeam.builder()
//                .leagueTeamName("mancity")
//                .teamType(TeamType.LEAGUE)
//                .build();
//
//        LeagueTeam awayTeam = LeagueTeam.builder()
//                .leagueTeamName("mancity")
//                .teamType(TeamType.LEAGUE)
//                .build();
//        Score score = Score.builder()
//                .homeScore(1)
//                .awayScore(3)
//                .build();
//        Season season = Season.builder()
//                .season("22-23")
//                .build();
//        String [] playerNames = new String[]{
//                "kdb",
//                "ederson",
//                "dias",
//                "sonny",
//                "royal",
//                "maddison",
//                "vicario",
//                "이강인",
//                "김민재",
//                "황희찬",
//                "조현우"
//        };
//
//        PlayerPosition [] playerPositions = new PlayerPosition[]{
//                PlayerPosition.MID_FIELDER,
//                PlayerPosition.MID_FIELDER,
//                PlayerPosition.MID_FIELDER,
//                PlayerPosition.MID_FIELDER,
//                PlayerPosition.MID_FIELDER,
//                PlayerPosition.MID_FIELDER,
//                PlayerPosition.MID_FIELDER,
//                PlayerPosition.KEEPER,
//                PlayerPosition.DEFENDER,
//                PlayerPosition.FORWARD,
//                PlayerPosition.KEEPER,
//        };
//
//        List<Player> homePlayers = new ArrayList<>();
//        List<Player> awayPlayers = new ArrayList<>();
//        List<LeagueGamePlayer> homeLeagueGamePlayers = new ArrayList<>();
//        List<LeagueGamePlayer> awayLeagueGamePlayers = new ArrayList<>();
//
//        for (int i = 0; i < playerNames.length; i++)
//        {
//            Player homePlayer = createPlayer(playerNames[i], homeTeam, playerPositions[i]);
//            Player awayPlayer = createPlayer(playerNames[i], awayTeam, playerPositions[i]);
//            homePlayers.add(homePlayer);
//            awayPlayers.add(awayPlayer);
//            homeLeagueGamePlayers.add(createLeagueGamePlayer(homePlayer, homePlayer.getPosition()));
//            awayLeagueGamePlayers.add(createLeagueGamePlayer(awayPlayer, awayPlayer.getPosition()));
//        }
//
//        Assertions.assertThatThrownBy(() -> LeagueGame.builder()
//                        .leagueGameStatus(LeagueGameStatus.GAMING)
//                        .gameDate(LocalDate.of(2024,11,24).atStartOfDay())
//                        .count(2)
//                        .away(awayTeam)
//                        .home(homeTeam)
//                        .score(score)
//                        .season(season)
//                        .homePlayers(homeLeagueGamePlayers)
//                        .awayPlayers(awayLeagueGamePlayers)
//                        .build())
//                .hasMessage("키퍼가 1명 아님")
//                .isInstanceOf(IllegalArgumentException.class);
//    }
//}
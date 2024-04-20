package com.kickoff.api.service.soccer.player;

import com.kickoff.api.service.soccer.player.dto.CreatePlayerServiceRequest;
import com.kickoff.domain.soccer.player.Player;
import com.kickoff.domain.soccer.player.PlayerRepository;
import com.kickoff.domain.soccer.team.league.LeagueTeamRepository;
import com.kickoff.domain.team.league.LeagueTeam;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final LeagueTeamRepository leagueTeamRepository;

    public Long createPlayer(CreatePlayerServiceRequest request)
    {
        LeagueTeam leagueTeam = leagueTeamRepository.findById(request.leagueTeamId())
                .orElseThrow(()->new IllegalArgumentException());

        Player player = Player.builder()
                .playerName(request.playerName())
                .national(request.national())
                .leagueTeam(leagueTeam)
                .position(request.playerPosition())
                .build();

        return playerRepository.save(player).getPlayerId();
    }



}

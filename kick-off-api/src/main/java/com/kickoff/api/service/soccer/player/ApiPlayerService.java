package com.kickoff.api.service.soccer.player;

import com.kickoff.api.service.soccer.player.dto.CreatePlayerServiceRequest;
import com.kickoff.domain.soccer.player.Player;
import com.kickoff.domain.soccer.player.PlayerRepository;
import com.kickoff.domain.soccer.team.league.LeagueTeam;
import com.kickoff.domain.soccer.team.league.LeagueTeamRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ApiPlayerService {
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

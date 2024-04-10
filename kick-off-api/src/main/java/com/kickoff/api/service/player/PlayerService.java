package com.kickoff.api.service.player;

import com.kickoff.api.service.player.dto.CreatePlayerServiceRequest;
import com.kickoff.domain.player.Player;
import com.kickoff.domain.player.PlayerRepository;
import com.kickoff.domain.player.QPlayer;
import com.kickoff.domain.team.league.LeagueTeam;
import com.kickoff.domain.team.league.LeagueTeamRepository;
import com.kickoff.domain.team.league.QLeagueTeam;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.Optional;

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

package com.kickoff.domain.soccer.player.service;

import com.kickoff.domain.soccer.player.Player;
import com.kickoff.domain.soccer.player.PlayerQuerydslRepository;
import com.kickoff.domain.soccer.player.PlayerRepository;
import com.kickoff.domain.soccer.player.service.dto.CreatePlayerRequest;
import com.kickoff.domain.soccer.team.league.LeagueTeamRepository;
import com.kickoff.domain.team.league.LeagueTeam;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final LeagueTeamRepository leagueTeamRepository;
    private final PlayerQuerydslRepository playerQuerydslRepository;


    public Long save(CreatePlayerRequest request)
    {
        LeagueTeam leagueTeam = leagueTeamRepository.findById(request.leagueTeamId()).orElseThrow(() -> new EntityNotFoundException());

        Player player = Player.builder()
                .playerName(request.playerName())
                .leagueTeam(leagueTeam)
                .national(request.national())
                .position(request.playerPosition()).build();

        return playerRepository.save(player).getPlayerId();
    }

    public List<Player> findPlayers() {
        return playerRepository.findAll();
    }

    public Player findPlayerById(Long playerId) {
        return playerQuerydslRepository.findPlayer(playerId).orElseThrow((() -> new EntityNotFoundException()));
    }

    public List<Player> findPlayer(String playerName,String national,Long leagueTeamId){
        return playerQuerydslRepository.findAllByUsers(playerName,national,leagueTeamId);
    }

}

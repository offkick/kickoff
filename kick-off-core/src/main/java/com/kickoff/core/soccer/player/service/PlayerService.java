package com.kickoff.core.soccer.player.service;

import com.kickoff.core.soccer.player.Player;
import com.kickoff.core.soccer.player.PlayerQuerydslRepository;
import com.kickoff.core.soccer.player.PlayerRepository;
import com.kickoff.core.soccer.player.PlayerRepositoryImpl;
import com.kickoff.core.soccer.player.dto.PlayerDTO;
import com.kickoff.core.soccer.player.dto.PlayerSearchCondition;
import com.kickoff.core.soccer.player.service.dto.CreatePlayerRequest;
import com.kickoff.core.soccer.team.league.LeagueTeam;
import com.kickoff.core.soccer.team.league.LeagueTeamRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final LeagueTeamRepository leagueTeamRepository;
    private final PlayerQuerydslRepository playerQuerydslRepository;
    private final PlayerRepositoryImpl playerRepositoryImpl;

    public Long save(CreatePlayerRequest request)
    {
        LeagueTeam leagueTeam = leagueTeamRepository.findById(request.leagueTeamId()).orElseThrow(EntityNotFoundException::new);

        Player player = Player.builder()
                .playerName(request.playerName())
                .leagueTeam(leagueTeam)
                .national(request.national())
                .position(request.playerPosition()).build();

        return playerRepository.save(player).getPlayerId();
    }

    public List<Player> findPlayers()
    {
        return playerRepository.findAll();
    }

    public Player findPlayerById(Long playerId)
    {
        return playerQuerydslRepository.findPlayer(playerId).orElseThrow(EntityNotFoundException::new);
    }

    public List<PlayerDTO> findPlayer(String playerName, String national, Long leagueTeamId)
    {
        return playerQuerydslRepository.findAllByUsers(playerName,national,leagueTeamId);
    }

    public Page<PlayerDTO> searchPlayers(PlayerSearchCondition condition, Pageable pageable)
    {
        return playerRepositoryImpl.searchPlayer(condition,pageable);
    }

    public PlayerDTO findPlayers(Long playerId)
    {
        Player player = playerRepository.findById(playerId).orElseThrow(EntityNotFoundException::new);
        return PlayerDTO.from(player);
    }
}

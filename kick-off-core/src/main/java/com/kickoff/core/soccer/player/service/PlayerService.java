package com.kickoff.core.soccer.player.service;

import com.kickoff.core.soccer.player.*;
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
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final LeagueTeamRepository leagueTeamRepository;
    private final PlayerQuerydslRepository playerQuerydslRepository;
    private final PlayerRepositoryImpl playerRepositoryImpl;
    private final PlayerImageRepository playerImageRepository;

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
    
    public Player addPlayerImage(Long playerId, String imageUrl){
        Optional<Player> player = playerRepository.findById(playerId);
        if(player.isPresent()) {
            Player player1 = player.get();
            PlayerImage playerImage = new PlayerImage(imageUrl, player1);
            player1.getPlayerImages().add(playerImage);
            playerImageRepository.save(playerImage);
            return player1;
        }else{
            throw new RuntimeException();
        }
    }

    public Player findPlayerById(Long playerId)
    {
        return playerQuerydslRepository.findPlayer(playerId).orElseThrow(EntityNotFoundException::new);
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

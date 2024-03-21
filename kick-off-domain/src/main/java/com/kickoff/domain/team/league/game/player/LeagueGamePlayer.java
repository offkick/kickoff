package com.kickoff.domain.team.league.game.player;

import com.kickoff.domain.player.Player;
import com.kickoff.domain.player.PlayerPosition;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class LeagueGamePlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long LeagueGamePlayerId;

    @Enumerated(EnumType.STRING)
    private LeagueGamePlayerStatus status;

    private int playedTime;

    private int subTime;

    @Enumerated(EnumType.STRING)
    private PlayerPosition position;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id")
    private Player player;
}

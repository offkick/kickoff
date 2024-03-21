package com.kickoff.domain.team.league.game.player;

import com.kickoff.domain.board.player.Player;
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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id")
    private Player player;
}

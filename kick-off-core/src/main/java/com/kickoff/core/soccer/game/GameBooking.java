package com.kickoff.core.soccer.game;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Embeddable
@Builder
@AllArgsConstructor
public class GameBooking {
    private Long minute;
    private Long leagueTeamId;
    private Long playerId;
    private String type;
    private String playerKrName;
    private String playerEnName;

    private String cardType;
}

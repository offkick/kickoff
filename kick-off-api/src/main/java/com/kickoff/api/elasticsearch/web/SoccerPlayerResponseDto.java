package com.kickoff.api.elasticsearch.web;

import com.kickoff.domain.soccer.player.PlayerPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SoccerPlayerResponseDto {
    private Long playerId;
    private String teamName;
    private Long teamId;
    private String playerName;
    private PlayerPosition playerPosition;
    private String nation;
    private Integer age;
}

package com.kickoff.api.elasticsearch.index;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SoccerPlayerIndex {
    private String playerId;
    private String teamName;
    private String teamId;
    private String playerName;
    private String playerPosition;
    private String nation;
    private String age;
}

package com.kickoff.api.elasticsearch.index;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// index : {
//    playerId: 1,
//    teamName: "토트넘",
//    teamId: 2,
//    playerName: "손흥민",
//    position : "FORWARD",
//    nation : "KOREA",
//    age : 32,
//
// }
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

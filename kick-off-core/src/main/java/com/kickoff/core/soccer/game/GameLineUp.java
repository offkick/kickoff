package com.kickoff.core.soccer.game;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Embeddable
public class GameLineUp {
      private Long playerId;
      private String position;
      private String shirtNumber;
      private String playerKrName;
      private String playerEnName;
      private String type;

      public GameLineUp(Long playerId, String position, String shirtNumber, String type, String playerKrName, String playerEnName) {
            this.playerId = playerId;
            this.position = position;
            this.shirtNumber = shirtNumber;
            this.type = type;
            this.playerKrName = playerKrName;
            this.playerEnName = playerEnName;
      }
}

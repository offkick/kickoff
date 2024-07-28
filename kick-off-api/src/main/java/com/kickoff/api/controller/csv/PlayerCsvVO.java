package com.kickoff.api.controller.csv;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlayerCsvVO {
    private String playerName;
    private String position;
    private String team;
}
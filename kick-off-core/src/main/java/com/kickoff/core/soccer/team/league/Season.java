package com.kickoff.core.soccer.team.league;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "season")
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seasonId;

    private String years;

    @Builder
    public Season(Long seasonId, String years)
    {
        this.seasonId = seasonId;
        this.years = years;
    }
}

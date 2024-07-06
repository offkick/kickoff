package com.kickoff.core.soccer.team.league;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Year;

@Entity
@Getter
@NoArgsConstructor
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seasonId;

    private String year;

    @Builder
    public Season(Long seasonId, String year)
    {
        this.seasonId = seasonId;
        this.year = year;
    }
}

package com.kickoff.domain.team.league;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seasonId;

    // TODO : Pattern ==> 23-24
    private String season;

    @Builder
    public Season(Long seasonId, String season) {
        this.seasonId = seasonId;
        this.season = season;
    }
}

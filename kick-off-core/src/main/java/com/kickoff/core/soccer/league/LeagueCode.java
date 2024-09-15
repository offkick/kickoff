package com.kickoff.core.soccer.league;

import com.kickoff.core.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class LeagueCode extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leagueId;
    private String leagueCode;
    private String caption;
    private String country;
    private String leagueCodeName;
}

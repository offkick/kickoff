package com.kickoff.core.soccer.team.league;

import com.kickoff.core.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "leagueCode")
public class LeagueCode extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leagueId;
    private String leagueCode;
    private String caption;
    private String country;
    private String leagueCodeName;
}

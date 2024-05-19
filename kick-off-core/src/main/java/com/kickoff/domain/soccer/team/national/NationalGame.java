package com.kickoff.domain.soccer.team.national;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class NationalGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nationalScheduleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "national_team_id")
    private NationalTeam nationalTeam;

    // TODO : NationalGame
    @Enumerated(EnumType.STRING)
    private NationalGameType nationalGameType;
}

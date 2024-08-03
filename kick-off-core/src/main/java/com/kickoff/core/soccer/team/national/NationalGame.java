package com.kickoff.core.soccer.team.national;

import com.kickoff.core.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class NationalGame extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nationalScheduleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "national_team_id")
    private NationalTeam nationalTeam;

    @Enumerated(EnumType.STRING)
    private NationalGameType nationalGameType;

    @Column(columnDefinition = "boolean default false")
    private Boolean isDeleted;


}

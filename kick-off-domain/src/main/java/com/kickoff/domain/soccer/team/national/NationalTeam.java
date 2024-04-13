package com.kickoff.domain.soccer.team.national;
import com.kickoff.domain.soccer.team.TeamType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class NationalTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nationalTeamId;

    private String nationalTeamName;

    @Enumerated(EnumType.STRING)
    private TeamType teamType;

    @Builder
    public NationalTeam(Long nationalTeamId, String nationalTeamName, TeamType teamType) {
        this.nationalTeamId = nationalTeamId;
        this.nationalTeamName = nationalTeamName;
        this.teamType = teamType;
    }
}

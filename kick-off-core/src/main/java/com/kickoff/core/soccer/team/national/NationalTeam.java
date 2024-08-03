package com.kickoff.core.soccer.team.national;
import com.kickoff.core.BaseEntity;
import com.kickoff.core.soccer.team.TeamType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class NationalTeam extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nationalTeamId;

    private String nationalTeamName;

    @Enumerated(EnumType.STRING)
    private TeamType teamType;

    @Column(columnDefinition = "boolean default false")
    private Boolean isDeleted;

    @Builder
    public NationalTeam(Long nationalTeamId, String nationalTeamName, TeamType teamType) {
        this.nationalTeamId = nationalTeamId;
        this.nationalTeamName = nationalTeamName;
        this.teamType = teamType;
        isDeleted = Boolean.FALSE;
    }

    public void delete()
    {
        if (!isDeleted)
        {
            this.isDeleted = true;
        }
    }
}

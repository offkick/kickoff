package com.domain.team.national;
import com.domain.team.TeamType;
import jakarta.persistence.*;
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

}

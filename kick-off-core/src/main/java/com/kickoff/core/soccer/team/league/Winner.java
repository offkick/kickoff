package com.kickoff.core.soccer.team.league;

import com.kickoff.core.BaseEntity;
import com.kickoff.core.soccer.team.TeamType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@ToString
public class Winner extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long winnerId;

    private String winnerName;
    @Column(columnDefinition = "boolean default false")
    private Boolean isDeleted;

    @Builder
    public Winner(Long winnerId, String winnerTeamName) {
        this.winnerId = winnerId;
        this.winnerName = winnerTeamName;
    }
    public void delete()
    {
        if (!isDeleted)
        {
            this.isDeleted = true;
        }
    }
}

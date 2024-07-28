package com.kickoff.core.soccer.player;

import com.kickoff.core.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class PlayerComment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    @Column(columnDefinition = "boolean default false")
    private Boolean deleted;

    private Long playerId;
    private Long memberId;

    public PlayerComment(String comment, Long playerId, Long memberId)
    {
        this.comment = comment;
        this.deleted = false;
        this.playerId = playerId;
        this.memberId = memberId;
    }
}

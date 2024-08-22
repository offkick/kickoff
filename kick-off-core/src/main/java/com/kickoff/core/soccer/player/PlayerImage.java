package com.kickoff.core.soccer.player;

import com.kickoff.core.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class PlayerImage extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playerImageId;
    private String url;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playerId")
    private Player player;

    @Column(columnDefinition = "boolean default false")
    private Boolean isDeleted;

    public PlayerImage(String url, Player player)
    {
        this.url = url;
        this.player = player;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
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

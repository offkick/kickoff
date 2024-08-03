package com.kickoff.api.service.board;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class BoardSearch {
    @Id
    private Long postId;
    private Integer viewCount;
    private Integer likeCount;
}

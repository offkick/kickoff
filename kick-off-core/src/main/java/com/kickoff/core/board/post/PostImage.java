package com.kickoff.core.board.post;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class PostImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;
    private String url;
    private int postImageOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public PostImage(Long imageId, String url, int postImageOrder, Post post) {
        this.imageId = imageId;
        this.url = url;
        this.postImageOrder = postImageOrder;
        this.post = post;
    }
}

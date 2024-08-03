package com.kickoff.core.board.postImage;

import com.kickoff.core.BaseEntity;
import com.kickoff.core.board.post.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class PostImage extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;
    private String url;
    private int postImageOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(columnDefinition = "boolean default false")
    private Boolean isDeleted;

    public PostImage(Long imageId, String url, int postImageOrder, Post post)
    {
        this.imageId = imageId;
        this.url = url;
        this.postImageOrder = postImageOrder;
        this.post = post;
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

package com.kickoff.core.board.postlike;

import com.kickoff.core.BaseEntity;
import com.kickoff.core.board.post.Post;
import com.kickoff.core.member.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(indexes = @Index(name = "post_like_idx", unique = true, columnList = "member_id, post_id"))
public class PostLike extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postLikeId;

    @JoinColumn(name = "post_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Column(columnDefinition = "boolean default false")
    private Boolean isDeleted;


    @Builder
    public PostLike(Long postLikeId, Post post, Member member) {
        this.postLikeId = postLikeId;
        this.post = post;
        this.member = member;
    }

    public void delete()
    {
        if (!isDeleted)
        {
            this.isDeleted = true;
        }
    }

}

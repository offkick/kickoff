package com.kickoff.core.board.postcomment;

import com.kickoff.core.BaseEntity;
import com.kickoff.core.board.post.Post;
import com.kickoff.core.member.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class PostComment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(columnDefinition = "boolean default false")
    private Boolean isDeleted;

    public void update(PostComment updateComment)
    {
        setPostComment(updateComment.getComment());
    }

    private void setPostComment(String comment)
    {
        if(comment == null || comment.isBlank())
        {
            return;
        }
        this.comment=comment;

    }

    public void delete()
    {
        if (!isDeleted)
        {
            this.isDeleted = true;
        }
    }

    @Builder
    public PostComment(Long commentId, String comment, Member member, Post post) {
        this.commentId = commentId;
        this.comment = comment;
        this.member = member;
        this.post = post;
        isDeleted = Boolean.FALSE;
    }
}

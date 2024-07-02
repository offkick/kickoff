package com.kickoff.core.board.postcomment;

import com.kickoff.core.board.post.Post;
import com.kickoff.core.member.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class PostComment {

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

    @Builder
    public PostComment(Long commentId, String comment, Member member, Post post) {
        this.commentId = commentId;
        this.comment = comment;
        this.member = member;
        this.post = post;
    }
}

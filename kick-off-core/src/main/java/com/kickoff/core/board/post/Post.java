package com.kickoff.core.board.post;

import com.kickoff.core.board.postcomment.PostComment;
import com.kickoff.core.member.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String title;

    private String content;

    private String category;

    private LocalDateTime postDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Post(Long postId, String title, String content, String category, LocalDateTime postDate, Member member) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.category = category;
        this.postDate = postDate;
        this.member = member;
    }

    public void update(Post updatePost)
    {
        setTitle(updatePost.getTitle());
        setContent(updatePost.getContent());
        setCategory(updatePost.getCategory());
    }

    private void setContent(String content)
    {
        if(content == null || content.isBlank())
        {
            return ;
        }
        this.content = content;
    }
    private void setTitle(String title)
    {
        if(title == null || title.isBlank())
        {
            return ;
        }
        this.title = title;
    }
    private void setCategory(String category)
    {
        if(category == null || category.isBlank())
        {
            return ;
        }
        this.category = category;
    }
}

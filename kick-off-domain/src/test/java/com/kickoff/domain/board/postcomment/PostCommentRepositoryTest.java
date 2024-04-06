package com.kickoff.domain.board.postcomment;

import com.kickoff.domain.TestConfiguration;
import com.kickoff.domain.member.Member;
import com.kickoff.domain.member.MemberRepository;
import com.kickoff.domain.board.post.Post;
import com.kickoff.domain.board.post.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = TestConfiguration.class)
@DataJpaTest
public class PostCommentRepositoryTest {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PostCommentRepository postCommentRepository;

    @Test
    public void save(){
        Member member = memberRepository.save(
                Member.builder()
                        .nickName("hi")
                        .password("password")
                        .build()
        );
        Post post = postRepository.save(
                Post.builder()
                        .title("title")
                        .category("category")
                        .member(member)
                        .build()
        );
        PostComment postComment = postCommentRepository.save(
                PostComment.builder()
                        .comment("comment")
                        .post(post)
                        .member(member)
                        .build()
        );
        assertThat(postComment.getCommentId()).isNotNull();

    }


}

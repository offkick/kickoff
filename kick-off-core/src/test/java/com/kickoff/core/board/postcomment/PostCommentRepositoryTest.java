package com.kickoff.core.board.postcomment;

import com.kickoff.core.TestConfiguration;
import com.kickoff.core.board.post.Post;
import com.kickoff.core.board.post.PostRepository;
import com.kickoff.core.member.Member;
import com.kickoff.core.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("core")
@ContextConfiguration(classes = TestConfiguration.class)
@SpringBootTest
public class PostCommentRepositoryTest {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PostCommentRepository postCommentRepository;

    @Test
    @Commit
    public void save(){
        Member member = memberRepository.save(
                Member.builder()
                        .nickname("hi")
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

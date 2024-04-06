package com.kickoff.domain.board.post;

import com.kickoff.domain.TestConfiguration;
import com.kickoff.domain.member.Member;
import com.kickoff.domain.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = TestConfiguration.class)
@DataJpaTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MemberRepository memberRepository;

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
        assertThat(post.getPostId()).isNotNull();
    }
}

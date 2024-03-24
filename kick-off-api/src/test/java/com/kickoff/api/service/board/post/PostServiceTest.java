package com.kickoff.api.service.board.post;

import com.kickoff.api.service.board.post.dto.CreatePostServiceRequest;
import com.kickoff.api.service.board.post.dto.UpdatePostServiceRequest;
import com.kickoff.domain.board.member.Member;
import com.kickoff.domain.board.member.MemberRepository;
import com.kickoff.domain.board.post.Post;
import com.kickoff.domain.board.post.PostRepository;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
public class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PostRepository postRepository;

    @Test
    void createPost()
    {
        //given
        Member member = memberRepository.save(
                Member.builder()
                        .nickName("hi")
                        .password("password")
                        .build()
        );

        CreatePostServiceRequest request = new CreatePostServiceRequest(
                "title",
                "content",
                "EPL"
        );

        //when
        Long postId = postService.createPost(member.getMemberId(),request);

        //then
        assertThat(postId).isNotNull();
    }

    @Test
    void updatePost(){
        //given
        Member member = memberRepository.save(
                Member.builder()
                        .nickName("hi")
                        .password("password")
                        .build()
        );

        Post post = postRepository.save(
                Post.builder()
                        .title("title")
                        .content("content")
                        .category("category")
                        .member(member)
                        .build()
        );

        UpdatePostServiceRequest request = new UpdatePostServiceRequest(
                "updatedTitle",
                "updatedContent",
                "updatedCategory",
                post.getPostId()
        );

        //when
        Long postId = postService.update(request);

        //then
        assertThat(postId).isNotNull();
    }

    @Test
    void deletePost()
    {
        //given
        Member member = memberRepository.save(
                Member.builder()
                        .nickName("hi")
                        .password("password")
                        .build()
        );

        Post post = postRepository.save(
                Post.builder()
                        .title("title")
                        .content("content")
                        .category("category")
                        .member(member)
                        .build()
        );

        //when
        postService.delete(post.getPostId());

        //then
        List<Post> all = postRepository.findAll();
        assertThat(all).isEmpty();

    }
}

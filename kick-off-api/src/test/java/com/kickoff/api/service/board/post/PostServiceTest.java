package com.kickoff.api.service.board.post;

import com.kickoff.api.service.board.post.dto.CreatePostServiceRequest;
import com.kickoff.api.service.board.post.dto.UpdatePostServiceRequest;
import com.kickoff.domain.member.Member;
import com.kickoff.domain.member.MemberRepository;
import com.kickoff.domain.board.post.Post;
import com.kickoff.domain.board.post.PostRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    @DisplayName("포스트생성")
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
        Long postId = postService.create(member.getMemberId(), request);

        //then
        assertThat(postId).isNotNull();
    }

    @Test
    @DisplayName("포스트수정")
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
    @DisplayName("포스트삭제")
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

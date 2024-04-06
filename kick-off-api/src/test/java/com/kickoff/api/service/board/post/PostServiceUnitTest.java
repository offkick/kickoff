package com.kickoff.api.service.board.post;

import com.kickoff.api.service.board.post.dto.CreatePostServiceRequest;
import com.kickoff.api.service.board.post.dto.UpdatePostServiceRequest;
import com.kickoff.domain.member.Member;
import com.kickoff.domain.member.MemberRepository;
import com.kickoff.domain.board.post.Post;
import com.kickoff.domain.board.post.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class)
class PostServiceUnitTest {

    @Mock
    PostRepository postRepository;

    @Mock
    MemberRepository memberRepository;

    @InjectMocks
    PostService postService;

    @Test
    public void createPost()
    {
        //given
        CreatePostServiceRequest request = new CreatePostServiceRequest("title", "content", "cate");
        given(memberRepository.findById(any())).willReturn(Optional.of(Member.builder().build()));

        //when
        postService.create(any(), request);

        //then
        BDDMockito.verify(postRepository, times(1)).save(any());
    }

    @Test
    public void updatePost()
    {
        //given
        Post post = Mockito.mock(Post.class);
        UpdatePostServiceRequest updatePostServiceRequest = new UpdatePostServiceRequest("title2", "content2", "category2", 1L);
        given(postRepository.findById(anyLong())).willReturn(Optional.of(post));

        //when
        postService.update(updatePostServiceRequest);

        //then
        BDDMockito.verify(post, times(1)).update(any());
    }

    @Test
    public void createFail()
    {
        //given
        CreatePostServiceRequest request = new CreatePostServiceRequest("title", "content", "cate");
        given(memberRepository.findById(any())).willReturn(Optional.empty());

        //when
        assertThatThrownBy(() ->postService.create(any(), request))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
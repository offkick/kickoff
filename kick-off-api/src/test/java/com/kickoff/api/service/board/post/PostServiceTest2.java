package com.kickoff.api.service.board.post;

import com.kickoff.api.service.board.post.dto.CreatePostServiceRequest;
import com.kickoff.domain.board.member.Member;
import com.kickoff.domain.board.member.MemberRepository;
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


@ExtendWith(MockitoExtension.class)
class PostServiceTest2 {

    @Mock
    PostRepository postRepository;

    @Mock
    MemberRepository memberRepository;

    @InjectMocks
    PostService sut;

    @Test
    public void createPost()
    {
        BDDMockito.given(memberRepository.findById(Mockito.any()))
                        .willReturn(Optional.of(new Member()));

        sut.createPost(1L, new CreatePostServiceRequest("title", "content", "cate"));
    }
}
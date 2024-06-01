package com.kickoff.api.service.board.postlike;

import com.kickoff.api.service.board.postlike.dto.PostLikeServiceRequest;
import com.kickoff.core.board.post.Post;
import com.kickoff.core.board.post.PostRepository;
import com.kickoff.core.board.postlike.PostLike;
import com.kickoff.core.board.postlike.PostLikeRepository;
import com.kickoff.core.member.Member;
import com.kickoff.core.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@SpringBootTest
public class PostLikeServiceTest {
    @Autowired
    PostRepository postRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PostLikeRepository postLikeRepository;

    @Autowired
    PostLikeService postLikeService;

    @Test
    @DisplayName("좋아요 테스트")
    void like(){
        Member member = memberRepository.save(
                Member.builder()
                        .nickName("name")
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

        PostLikeServiceRequest request = new PostLikeServiceRequest(
                post.getPostId(),
                member.getMemberId()
        );

        postLikeService.like(request);

        List<PostLike> all = postLikeRepository.findAll();

        Assertions.assertThat(all).hasSize(1);

    }
}

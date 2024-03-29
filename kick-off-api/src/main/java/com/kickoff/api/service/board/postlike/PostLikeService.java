package com.kickoff.api.service.board.postlike;

import com.kickoff.api.service.board.postlike.dto.PostLikeServiceRequest;
import com.kickoff.domain.board.member.Member;
import com.kickoff.domain.board.member.MemberRepository;
import com.kickoff.domain.board.post.Post;
import com.kickoff.domain.board.post.PostRepository;
import com.kickoff.domain.board.postlike.PostLike;
import com.kickoff.domain.board.postlike.PostLikeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PostLikeService {
    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public void like(PostLikeServiceRequest request)
    {
        Member member = memberRepository.findById(request.memberId())
                .orElseThrow(() -> new IllegalArgumentException());

        Post post = postRepository.findById(request.postId())
                .orElseThrow(() -> new IllegalArgumentException());

        PostLike postLike = PostLike.builder()
                .member(member)
                .post(post)
                .build();

        postLikeRepository.save(postLike);
    }
}

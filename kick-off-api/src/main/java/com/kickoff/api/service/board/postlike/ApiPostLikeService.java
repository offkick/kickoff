package com.kickoff.api.service.board.postlike;

import com.kickoff.api.service.board.postlike.dto.PostLikeServiceRequest;
import com.kickoff.core.board.post.Post;
import com.kickoff.core.board.post.PostRepository;
import com.kickoff.core.board.postlike.PostLike;
import com.kickoff.core.board.postlike.PostLikeRepository;
import com.kickoff.core.member.Member;
import com.kickoff.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ApiPostLikeService {
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

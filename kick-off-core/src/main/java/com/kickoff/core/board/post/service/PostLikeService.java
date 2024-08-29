package com.kickoff.core.board.post.service;

import com.kickoff.core.board.post.Post;
import com.kickoff.core.board.postlike.PostLike;
import com.kickoff.core.board.postlike.PostLikeRepository;
import com.kickoff.core.member.Member;
import com.kickoff.core.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class PostLikeService {
    private final PostLikeRepository postLikeRepository;
    private final MemberService memberService;
    private final PostService postService;

    public void save(PostLike postLike)
    {
        postLikeRepository.save(postLike);
    }

    public void like(Long memberId, Long postId)
    {
        Member member = memberService.findById(memberId);
        Post post = postService.findById(postId);

        if (postLikeRepository.existsByPostAndMember(post, member))
        {
            throw new IllegalArgumentException("이미 좋아요 누름")
        }

        postLikeRepository.save(PostLike.builder()
                .member(member)
                .post(post)
                .build());
    }

    public void checkLike(Long memberId, Long postId)
    {
        Member member = memberService.findById(memberId);
        Post post = postService.findById(postId);

        if (postLikeRepository.existsByPostAndMember(post, member))
        {
            throw new IllegalArgumentException("이미 좋아요 누름");
        }
    }
}

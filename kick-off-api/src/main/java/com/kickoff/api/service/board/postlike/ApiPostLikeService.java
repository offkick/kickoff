package com.kickoff.api.service.board.postlike;

import com.kickoff.api.service.board.postlike.dto.PostLikeServiceRequest;
import com.kickoff.core.board.post.Post;
import com.kickoff.core.board.post.service.PostLikeService;
import com.kickoff.core.board.post.service.PostService;
import com.kickoff.core.board.postlike.PostLike;
import com.kickoff.core.member.Member;
import com.kickoff.core.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ApiPostLikeService {
    private final PostLikeService postLikeService;
    private final PostService postService;
    private final MemberService memberService;

    public void like(PostLikeServiceRequest request)
    {
        Member member = memberService.findById(request.memberId());
        Post post = postService.findById(request.postId());

        PostLike postLike = PostLike.builder()
                .member(member)
                .post(post)
                .build();

        postLikeService.save(postLike);
    }
}

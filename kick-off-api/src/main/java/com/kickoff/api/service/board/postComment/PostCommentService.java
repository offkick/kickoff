package com.kickoff.api.service.board.postComment;

import com.kickoff.core.config.security.AuthUtil;
import com.kickoff.api.controller.board.postComment.dto.PostCommentCreateRequest;
import com.kickoff.core.board.post.Post;
import com.kickoff.core.board.post.PostRepository;
import com.kickoff.core.board.postcomment.PostComment;
import com.kickoff.core.board.postcomment.PostCommentRepository;
import com.kickoff.core.member.Member;
import com.kickoff.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class PostCommentService {
    private final PostCommentRepository postCommentRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public Long create(Long postId, PostCommentCreateRequest request)
    {
        Post post = postRepository.findById(postId).orElseThrow();
        Long memberId = AuthUtil.currentUserId();

        Member member = memberRepository.findById(memberId).orElseThrow();

        PostComment postComment = PostComment.builder()
                .comment(request.content())
                .post(post)
                .member(member)
                .build();

        PostComment save = postCommentRepository.save(postComment);
        return save.getCommentId();
    }
}

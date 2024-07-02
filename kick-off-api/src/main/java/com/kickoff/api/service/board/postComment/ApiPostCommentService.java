package com.kickoff.api.service.board.postComment;

import com.kickoff.api.service.board.postComment.dto.CreateCommentServiceRequest;
import com.kickoff.core.board.post.Post;
import com.kickoff.core.board.post.PostRepository;
import com.kickoff.core.board.postcomment.PostComment;
import com.kickoff.core.board.postcomment.PostCommentRepository;
import com.kickoff.core.member.Member;
import com.kickoff.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class ApiPostCommentService {
    private final PostCommentRepository postCommentRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    public Long createComment(Long memberId, Long postId,CreateCommentServiceRequest request)
    {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException());
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException());

        PostComment postComment = PostComment.builder()
                .comment(request.comment())
                .member(member)
                .post(post)
                .commentId(request.commentId())
                .build();
        postCommentRepository.save(postComment);
        return postComment.getCommentId();
    }



    public void deleteComment(Long commentId,Long memberId)
    {
        PostComment postComment = postCommentRepository.findCommentsByMemberId(memberId, commentId)
                .orElseThrow(() -> new IllegalArgumentException());
        postCommentRepository.delete(postComment);
    }

//    public Long updateComment(Long memberId, Long postId, UpdateCommentServiceRequest request)
//    {
//        PostComment postComment = postCommentRepository.findCommentsByMemberId(memberId, commentId)
//                .orElseThrow(() -> new IllegalArgumentException());
//
//
//    }



}

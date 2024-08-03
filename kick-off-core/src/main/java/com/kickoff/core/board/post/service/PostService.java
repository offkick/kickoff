package com.kickoff.core.board.post.service;

import com.kickoff.core.board.post.Post;
import com.kickoff.core.board.post.PostRepository;
import com.kickoff.core.board.post.dto.CreatePostServiceRequest;
import com.kickoff.core.board.post.dto.UpdatePostServiceRequest;
import com.kickoff.core.member.Member;
import com.kickoff.core.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
@Log4j2
public class PostService {
    private final PostRepository postRepository;
    private final MemberService memberService;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addViewCount(Long postId)
    {
        postRepository.incrementViewCount(postId);
    }

    public Long update(Long postId, UpdatePostServiceRequest request)
    {
        Post post = findById(postId);
        Post updatePost = Post.builder()
                .title(request.title())
                .content(request.content())
                .category(request.category())
                .build();

        post.update(updatePost);
        return post.getPostId();
    }

    public Long create(Long memberId, CreatePostServiceRequest request)
    {
        Member member = memberService.findById(memberId);

        Post post = Post.builder()
                .title(request.title())
                .content(request.content())
                .category(request.category())
                .member(member)
                .build();

        postRepository.save(post);
        return post.getPostId();
    }

    public void delete(Long postId)
    {
        Post post = findById(postId);
        post.delete();
    }

    @Transactional(readOnly = true)
    public Post findById(Long postId)
    {
        return postRepository.findByPostIdAndIsDeletedFalse(postId).orElseThrow();
    }
}

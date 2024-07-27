package com.kickoff.core.board.post.service;

import com.kickoff.core.board.post.Post;
import com.kickoff.core.board.post.PostRepository;
import com.kickoff.core.board.post.dto.CreatePostServiceRequest;
import com.kickoff.core.board.post.dto.UpdatePostServiceRequest;
import com.kickoff.core.member.Member;
import com.kickoff.core.member.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public Long update(Long postId, UpdatePostServiceRequest request){
        Post post = postRepository.findById(postId).orElseThrow(IllegalArgumentException::new);
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
        Member member = memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new);

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
        Post post = postRepository.findById(postId).orElseThrow(EntityNotFoundException::new);
        postRepository.delete(post);
    }
}

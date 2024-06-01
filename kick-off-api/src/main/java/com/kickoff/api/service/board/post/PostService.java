package com.kickoff.api.service.board.post;

import com.kickoff.api.service.board.post.dto.CreatePostServiceRequest;
import com.kickoff.api.service.board.post.dto.UpdatePostServiceRequest;
import com.kickoff.core.board.post.Post;
import com.kickoff.core.board.post.PostRepository;
import com.kickoff.core.member.Member;
import com.kickoff.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public Long create(Long memberId,CreatePostServiceRequest request)
    {
        Optional<Member> byId = memberRepository.findById(memberId);
        Member member = byId.orElseThrow(() -> new IllegalArgumentException());

        Post post = Post.builder()
                .title(request.title())
                .content(request.content())
                .category(request.category())
                .member(member)
                .build();

        postRepository.save(post);
        return post.getPostId();

    }

    public void delete(Long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new IllegalArgumentException());
        postRepository.delete(post);
    }

    public Long update(UpdatePostServiceRequest request){
        Post post = postRepository.findById(request.postId())
                .orElseThrow(()-> new IllegalArgumentException());
        Post updatePost = Post.builder()
                .title(request.title())
                .content(request.content())
                .category(request.category())
                .build();

        post.update(updatePost);
        return post.getPostId();
    }
}

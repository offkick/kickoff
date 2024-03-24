package com.kickoff.api.service.board.post;

import com.kickoff.api.service.board.post.dto.CreatePostServiceRequest;
import com.kickoff.api.service.board.post.dto.UpdatePostServiceRequest;
import com.kickoff.domain.board.member.Member;
import com.kickoff.domain.board.member.MemberRepository;
import com.kickoff.domain.board.post.Post;
import com.kickoff.domain.board.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    public Long createPost(Long memberId,CreatePostServiceRequest request)
    {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()->new IllegalArgumentException());

        Post post = Post.builder()
                .title(request.title())
                .content(request.content())
                .category(request.category())
                .member(member)
                .build();

        return postRepository.save(post).getPostId();

    }

    public void delete(Long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new IllegalArgumentException());
        postRepository.delete(post);
    }

    public Long update(UpdatePostServiceRequest request){
        Post post = postRepository.findById(request.postId())
                .orElseThrow(()-> new IllegalArgumentException());
        Post updatePost =  Post.builder()
                .title(request.title())
                .content(request.content())
                .category(request.category())
                .build();
        post.update(updatePost);
        return post.getPostId();
    }
}

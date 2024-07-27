package com.kickoff.api.service.board.post;

import com.kickoff.api.config.security.AuthUtil;
import com.kickoff.api.service.board.post.dto.CreatePostRequest;
import com.kickoff.core.board.post.Post;
import com.kickoff.core.board.post.PostRepository;
import com.kickoff.core.board.post.dto.UpdatePostServiceRequest;
import com.kickoff.core.board.post.service.PostService;
import com.kickoff.core.member.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostApiService {
    private final PostService postService;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public Long create(CreatePostRequest request)
    {
        return postService.create(AuthUtil.currentUserId(), request.toServiceDto());
    }

    public void delete(Long postId)
    {
        Post post = postRepository.findById(postId).orElseThrow(EntityNotFoundException::new);
        postRepository.delete(post);
    }

    public Long update(Long postId, UpdatePostServiceRequest request)
    {
        return postService.update(postId, request);
    }
}

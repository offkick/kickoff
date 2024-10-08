package com.kickoff.api.service.board.post;

import com.kickoff.api.service.board.post.dto.CreatePostRequest;
import com.kickoff.core.board.post.dto.UpdatePostServiceRequest;
import com.kickoff.core.board.post.service.PostService;
import com.kickoff.core.config.security.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostCommandService {
    private final PostService postService;

    public Long create(CreatePostRequest request)
    {
        return postService.create(AuthUtil.currentUserId(), request.toServiceDto());
    }

    public void delete(Long postId)
    {
        postService.delete(postId);
    }

    public Long update(Long postId, UpdatePostServiceRequest request)
    {
        return postService.update(postId, request);
    }
}

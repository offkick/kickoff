package com.kickoff.api.controller.board.post;

import com.kickoff.api.service.board.post.PostApiService;
import com.kickoff.api.service.board.post.dto.CreatePostRequest;
import com.kickoff.core.board.post.dto.UpdatePostServiceRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "게시물 컨트롤러", description = "게시물 관련 CUD")
@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostApiService postApiService;

    @PostMapping
    public Long createPost(@RequestBody CreatePostRequest request)
    {
        return postApiService.create(request);
    }

    @PutMapping("/{postId}")
    public Long updatePost(@PathVariable Long postId, @RequestBody UpdatePostServiceRequest request)
    {
        return postApiService.update(postId, request.toServiceDto());
    }
    
    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable(value="postId") Long postId)
    {
        postApiService.delete(postId);
    }
}
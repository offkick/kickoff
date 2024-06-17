package com.kickoff.api.controller.board.post;

import com.kickoff.api.service.board.post.PostService;
import com.kickoff.api.service.board.post.dto.CreatePostServiceRequest;
import com.kickoff.api.service.board.post.dto.UpdatePostServiceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;

    @PostMapping("/{memberId}")
    public Long createPost(@PathVariable(value="memberId") Long memberId,
        @RequestBody CreatePostServiceRequest request)
    {
        return postService.create(memberId,request.toServiceDto());
    }

    @PutMapping("/{postId}")
    public Long updatePost(@RequestBody UpdatePostServiceRequest request)
    {
        return postService.update(request.toServiceDto());
    }
    
    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable(value="postId") Long postId)
    {
        postService.delete(postId);
    }

}

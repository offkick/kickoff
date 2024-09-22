package com.kickoff.api.controller.board.post;

import com.kickoff.api.service.board.post.PostCommandService;
import com.kickoff.api.service.board.post.dto.CreatePostRequest;
import com.kickoff.core.board.post.dto.UpdatePostServiceRequest;
import com.kickoff.core.board.post.service.PostLikeService;
import com.kickoff.core.config.security.AuthUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "게시물 컨트롤러", description = "게시물 관련 CUD")
@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostCommandService postCommandService;
    private final PostLikeService postLikeService;

    @PostMapping
    public Long createPost(@RequestBody CreatePostRequest request)
    {
        return postCommandService.create(request);
    }

    @PutMapping("/{postId}")
    public Long updatePost(@PathVariable Long postId, @RequestBody UpdatePostServiceRequest request)
    {
        return postCommandService.update(postId, request.toServiceDto());
    }
    
    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable(value="postId") Long postId)
    {
        postCommandService.delete(postId);
    }

    @PostMapping("/like/{postId}")
    public void postLike(@PathVariable Long postId)
    {
        postLikeService.like(AuthUtil.currentUserId(), postId);
    }

    @GetMapping("/like/{postId}")
    public void like(@PathVariable Long postId)
    {
        postLikeService.checkLike(AuthUtil.currentUserId(), postId);
    }

    @GetMapping("/test")
    public String dd(){
        return "TTTT";
    }
}
package com.kickoff.api.controller.board.post;

import com.kickoff.api.config.security.AuthUtil;
import com.kickoff.api.service.board.post.ApiPostService;
import com.kickoff.api.service.board.post.dto.CreatePostServiceRequest;
import com.kickoff.api.service.board.post.dto.UpdatePostServiceRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "게시물 컨트롤러", description = "게시물 관련 CUD")
@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final ApiPostService apiPostService;

    @PostMapping
    public Long createPost(@RequestBody CreatePostServiceRequest request)
    {
        return apiPostService.create(AuthUtil.currentUserId(), request.toServiceDto());
    }

    @PutMapping("/{postId}")
    public Long updatePost(@PathVariable Long postId, @RequestBody UpdatePostServiceRequest request)
    {
        return apiPostService.update(postId, request.toServiceDto());
    }
    
    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable(value="postId") Long postId)
    {
        apiPostService.delete(postId);
    }
}

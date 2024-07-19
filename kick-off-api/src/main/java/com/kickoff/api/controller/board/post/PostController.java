package com.kickoff.api.controller.board.post;

import com.kickoff.api.service.board.post.ApiPostService;
import com.kickoff.api.service.board.post.dto.CreatePostServiceRequest;
import com.kickoff.api.service.board.post.dto.UpdatePostServiceRequest;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "게시물 컨트롤러", description = "게시물 관련 CUD")
@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final ApiPostService apiPostService;

    @PostMapping("/{memberId}")
    @Parameters({
            @Parameter(name = "memberId", description = "게시물 생성하는 멤버id"),
    })
    public Long createPost(@PathVariable(value="memberId") Long memberId,
        @RequestBody CreatePostServiceRequest request)
    {
        return apiPostService.create(memberId,request.toServiceDto());
    }

    @PutMapping("/{postId}")
    public Long updatePost(
            @PathVariable(value = "postId") Long postId,
            @RequestBody UpdatePostServiceRequest request)
    {
        return apiPostService.update(postId,request.toServiceDto());
    }
    
    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable(value="postId") Long postId)
    {
        apiPostService.delete(postId);
    }

}

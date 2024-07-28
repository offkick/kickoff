package com.kickoff.api.controller.board.postComment;

import com.kickoff.api.controller.board.postComment.dto.PostCommentCreateRequest;
import com.kickoff.api.service.board.postComment.PostCommentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "댓글 컨트롤러", description = "댓글 수정/삭제")
@RequiredArgsConstructor
@RequestMapping("/comment")
@RestController
public class PostCommentController {
    private final PostCommentService postCommentService;

    @PostMapping("/{postId}")
    public Long createComments(@PathVariable(value = "postId") Long postId, @RequestBody PostCommentCreateRequest request)
    {
        return postCommentService.create(postId, request);
    }
}

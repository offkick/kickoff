package com.kickoff.api.controller.board.postComment;

import com.kickoff.api.controller.board.postComment.dto.PostCommentResponse;
import com.kickoff.core.board.postcomment.PostCommentRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "댓글 조회 컨트롤러", description = "댓글 조회")
@RequiredArgsConstructor
@RequestMapping("/postComment/search")
@RestController
public class PostCommentSearchController {
    private final PostCommentRepository postCommentRepository;

    @GetMapping("/{postId}")
    public PostCommentResponse findComments(@PathVariable(value = "postId") Long postId)
    {
        return PostCommentResponse.of(postCommentRepository.findCommentsByPostId(postId));
    }

}

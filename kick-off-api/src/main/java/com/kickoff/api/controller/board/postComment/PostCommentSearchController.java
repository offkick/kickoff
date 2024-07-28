package com.kickoff.api.controller.board.postComment;

import com.kickoff.api.controller.board.postComment.dto.PostCommentResponse;
import com.kickoff.core.board.postcomment.PostCommentRepository;
import com.kickoff.core.config.security.AuthUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "댓글 조회 컨트롤러", description = "댓글 조회")
@RequiredArgsConstructor
@RequestMapping("/comment/search")
@RestController
public class PostCommentSearchController {
    private final PostCommentRepository postCommentRepository;

    @GetMapping("/{postId}")
    public PostCommentResponse findComments(@PathVariable(value = "postId") Long postId)
    {
        return PostCommentResponse.of(postCommentRepository.findCommentsByPostId(postId));
    }

    @GetMapping("/me")
    public PostCommentResponse findUserComments()
    {
        return PostCommentResponse.of(postCommentRepository.findCommentsByMemberId(AuthUtil.currentUserId()));
    }
}

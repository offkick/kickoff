package com.kickoff.api.controller.board.postComment;

import com.kickoff.api.controller.board.postComment.dto.PostCommentResponse;
import com.kickoff.core.board.postcomment.PostCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/postComment/search")
@RestController
public class PostCommentController {
    private final PostCommentRepository postCommentRepository;

    @GetMapping("/{postId}")
    public PostCommentResponse findComments(@PathVariable(value = "postId") Long postId)
    {
        return PostCommentResponse.of(postCommentRepository.findCommentsByPostId(postId));
    }
}

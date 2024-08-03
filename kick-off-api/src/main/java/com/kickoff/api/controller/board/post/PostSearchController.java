package com.kickoff.api.controller.board.post;

import com.kickoff.core.board.post.PostQuerydslRepository;
import com.kickoff.core.board.post.dto.PostSearchCondition;
import com.kickoff.core.board.post.dto.PostSearchResponse;
import com.kickoff.core.board.post.dto.PostSearchResponses;
import com.kickoff.core.config.security.AuthUtil;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/post/search")
@RestController
@Tag(name = "게시물 조회 컨트롤러", description = "모든 게시물 조회")
public class PostSearchController {
    private final PostQuerydslRepository postQuerydslRepository;

    @Parameters({
            @Parameter(name = "page", description = "페이지 번호, 기본 0"),
            @Parameter(name = "size", description = "페이지당 컨텐츠 개수, 기본 10")
    })
    @GetMapping
    public PostSearchResponses findPosts(
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "category", required = false) String postCategory
    ) {
        PostSearchCondition cond = new PostSearchCondition(
                PageRequest.of(page,size),
                postCategory
        );
        return postQuerydslRepository.findPosts(cond);
    }

    @Parameters({
            @Parameter(name = "postId", description = "게시물 ID")
    })
    @GetMapping("/{postId}")
    public PostSearchResponse findPost(@PathVariable Long postId, HttpServletRequest request, HttpServletResponse response)
    {
        return postQuerydslRepository.findPost(postId);
    }

    @GetMapping("/me")
    public PostSearchResponses findMyPosts()
    {
        return postQuerydslRepository.findMemberPosts(AuthUtil.currentUserId());
    }
}

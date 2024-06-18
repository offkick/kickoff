package com.kickoff.api.controller.board.post;

import com.kickoff.core.board.post.PostQuerydslRepository;
import com.kickoff.core.board.post.dto.FindPostCond;
import com.kickoff.core.board.post.dto.FindPostsResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/post/search")
@RestController
@Tag(name = "게시물 조회 컨트롤러", description = "모든 게시물 조회")
public class FindPostController {
    private final PostQuerydslRepository postQuerydslRepository;

    @Parameters({
            @Parameter(name = "page", description = "페이지 번호, 기본 0"),
            @Parameter(name = "size", description = "페이지당 컨텐츠 개수, 기본 10")
    })
    @GetMapping
    public FindPostsResponse findPosts(
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "category", required = false) String postCategory
    ) {
        FindPostCond cond = new FindPostCond(
                PageRequest.of(page,size),
                postCategory
        );
        return postQuerydslRepository.findPosts(cond);
    }

}

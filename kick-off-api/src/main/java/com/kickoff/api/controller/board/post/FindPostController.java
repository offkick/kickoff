package com.kickoff.api.controller.board.post;

import com.kickoff.core.board.post.PostQuerydslRepository;
import com.kickoff.core.board.post.dto.FindPostCond;
import com.kickoff.core.board.post.dto.FindPostsResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "게시글 컨트롤러", description = "게시글검색")
@RequiredArgsConstructor
@RequestMapping("/api/post/search")
@RestController
public class FindPostController {
    private final PostQuerydslRepository postQuerydslRepository;

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

package com.kickoff.core.board.post.dto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public record FindPostCond(
        Pageable pageable,
        String postCategory
) {
}

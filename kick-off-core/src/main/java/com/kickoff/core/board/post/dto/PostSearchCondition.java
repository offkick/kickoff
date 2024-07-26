package com.kickoff.core.board.post.dto;

import org.springframework.data.domain.Pageable;

public record PostSearchCondition(
        Pageable pageable,
        String postCategory
) {}

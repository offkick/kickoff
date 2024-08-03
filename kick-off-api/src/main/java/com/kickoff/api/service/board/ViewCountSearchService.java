package com.kickoff.api.service.board;

import java.util.Optional;

public interface ViewCountSearchService {
    Optional<BoardSearch> findByPostId(Long postId);
    void incrementViewCount(Long postId);
}

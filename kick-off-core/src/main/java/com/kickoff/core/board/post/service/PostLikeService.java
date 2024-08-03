package com.kickoff.core.board.post.service;

import com.kickoff.core.board.postlike.PostLike;
import com.kickoff.core.board.postlike.PostLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostLikeService {
    private final PostLikeRepository postLikeRepository;

    @Transactional
    public void save(PostLike postLike)
    {
        postLikeRepository.save(postLike);
    }
}

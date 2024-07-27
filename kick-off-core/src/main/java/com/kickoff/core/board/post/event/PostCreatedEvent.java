package com.kickoff.core.board.post.event;

public record PostCreatedEvent(
    Long postId,
    Long memberId
) {}

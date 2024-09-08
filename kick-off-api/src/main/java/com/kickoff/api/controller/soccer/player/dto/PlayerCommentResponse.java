package com.kickoff.api.controller.soccer.player.dto;

import com.kickoff.core.member.Member;
import com.kickoff.core.soccer.player.PlayerComment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record PlayerCommentResponse(
    List<PlayerCommentItem> playerComments
) {
    public static PlayerCommentResponse of(List<PlayerComment> comments, Map<Long, Member> map)
    {
        return new PlayerCommentResponse(comments.stream()
                .map(s -> new PlayerCommentItem(
                        s.getComment(),
                        s.getCreatedAt(),
                        map.get(s.getMemberId()).getNickname(),
                        map.get(s.getMemberId()).getMemberId()
                ))
                .sorted((a, b) -> b.createdAt.compareTo(a.createdAt))
                .collect(Collectors.toList()));
    }

    public record PlayerCommentItem(
        String comment,
        LocalDateTime createdAt,
        String nickname,
        Long memberId
    ) {}
}

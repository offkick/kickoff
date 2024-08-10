package com.kickoff.api.service.soccer.player;

import com.kickoff.api.controller.soccer.player.PlayerAddCommentRequest;
import com.kickoff.api.controller.soccer.player.PlayerCommentResponse;
import com.kickoff.api.controller.soccer.player.dto.AllPlayerResponse;
import com.kickoff.api.controller.soccer.player.dto.FindPlayerApiResponse;
import com.kickoff.core.config.security.AuthUtil;
import com.kickoff.core.member.Member;
import com.kickoff.core.member.service.MemberService;
import com.kickoff.core.soccer.player.PlayerComment;
import com.kickoff.core.soccer.player.PlayerCommentRepository;
import com.kickoff.core.soccer.player.dto.PlayerDTO;
import com.kickoff.core.soccer.player.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ApiPlayerService {
    private final PlayerService playerService;
    private final MemberService memberService;
    private final PlayerCommentRepository playerCommentRepository;

    public FindPlayerApiResponse findPlayers(Long playerId)
    {
        PlayerDTO players = playerService.findPlayers(playerId);
        return FindPlayerApiResponse.from(players);
    }

    public AllPlayerResponse findPlayers()
    {
        List<PlayerDTO> players = playerService.findAll();

        return new AllPlayerResponse(players.stream()
                .map(FindPlayerApiResponse::from)
                .collect(Collectors.toList()));
    }

    public void addComment(Long playerId, PlayerAddCommentRequest request)
    {
        playerCommentRepository.save(new PlayerComment(request.getComment(), playerId, AuthUtil.currentUserId()));
    }

    @Transactional(readOnly = true)
    public PlayerCommentResponse findPlayerComments(Long playerId)
    {
        List<PlayerComment> players = playerCommentRepository.findByPlayerIdOrderByCreatedAt(playerId);
        Set<Long> memberIds = players.stream()
                .map(PlayerComment::getMemberId)
                .collect(Collectors.toSet());

        Map<Long, Member> memberMap = memberService.findByIds(memberIds)
                .stream().collect(Collectors.toMap(Member::getMemberId, s->s));

        return PlayerCommentResponse.of(players, memberMap);
    }
}

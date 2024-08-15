package com.kickoff.api.service.board.member;

import com.kickoff.core.member.Member;
import com.kickoff.core.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberQueryService {
    private final MemberService memberService;

    @Transactional(readOnly = true)
    public MemberInfoResponse findMemberInfo(Long memberId)
    {
        Member member = memberService.findById(memberId);
        return new MemberInfoResponse(
                memberId,
                member.getEmail(),
                member.getNickName()
        );
    }
}

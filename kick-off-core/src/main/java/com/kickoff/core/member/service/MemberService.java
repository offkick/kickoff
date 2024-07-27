package com.kickoff.core.member.service;

import com.kickoff.core.member.Member;
import com.kickoff.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public Member findById(Long memberId)
    {
        return memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new);
    }
}

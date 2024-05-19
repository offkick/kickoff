package com.kickoff.core.survey.service;

import com.kickoff.core.member.Member;
import com.kickoff.core.member.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class MemberFindService {
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public Member findById(Long memberId)
    {
        return memberRepository.findById(memberId).orElseThrow(EntityNotFoundException::new);
    }
}

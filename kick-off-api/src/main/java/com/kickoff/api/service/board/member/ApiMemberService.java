package com.kickoff.api.service.board.member;

import com.kickoff.api.service.board.member.dto.CreateMemberServiceRequest;
import com.kickoff.api.service.board.member.dto.UpdateMemberServiceRequest;
import com.kickoff.core.member.Member;
import com.kickoff.core.member.MemberRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ApiMemberService {
    private final MemberRepository memberRepository;

    public Long createMember(CreateMemberServiceRequest request)
    {
        Member member = Member.builder()
                .nickName(request.nickName())
                .password(request.password())
                .build();

        return memberRepository.save(member).getMemberId();
    }

    public Long updateMember(UpdateMemberServiceRequest request)
    {
        Member member = memberRepository.findById(request.memberId())
                .orElseThrow(() -> new IllegalArgumentException());

        Member updateMember = Member.builder()
                .nickName(request.nickName())
                .password(request.password())
                .build();

        member.update(updateMember);
        return member.getMemberId();
    }

    public void deleteMember(Long memberId)
    {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException());
        memberRepository.delete(member);
    }

}

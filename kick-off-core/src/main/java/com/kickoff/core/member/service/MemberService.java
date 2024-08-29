package com.kickoff.core.member.service;

import com.kickoff.core.member.Member;
import com.kickoff.core.member.MemberRepository;
import com.kickoff.core.member.MemberRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public Member findById(Long memberId)
    {
        return memberRepository.findByMemberIdAndIsDeletedFalse(memberId).orElseThrow(IllegalArgumentException::new);
    }

    @Transactional
    public Member join(MemberJoinServiceRequest request)
    {
        if (!request.password().equals(request.confirmPassword()))
        {
            throw new MemberJoinException("비밀번호 검증 실패");
        }

        if (memberRepository.existsByEmail(request.email()))
        {
            throw new MemberJoinException("중복 이메일");
        }

        if (memberRepository.existsByNickName(request.nickname()))
        {
            throw new MemberJoinException("중복 닉네임");
        }

        Member member = Member.builder()
                .memberRoles(List.of(request.role()))
                .email(request.email())
                .nickname(request.nickname())
                .memberRoles(List.of(MemberRole.USER))
                .password(passwordEncoder.encode(request.password()))
                .build();

        return memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public Optional<Member> findByEmail(String email)
    {
        return memberRepository.findByEmail(email);
    }

    public Member save(Member member)
    {
        return memberRepository.save(member);
    }

    public void delete(Member member)
    {
        memberRepository.delete(member);
    }

    @Transactional(readOnly = true)
    public List<Member> findByIds(Set<Long> memberIds)
    {
        return memberRepository.findAllById(memberIds);
    }
}

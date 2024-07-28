package com.kickoff.core.member.service;

import com.kickoff.core.config.security.AuthUtil;
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
        return memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new);
    }

    @Transactional
    public void passwordChange(String currentPassword, String newPassword)
    {
        Member member = findById(AuthUtil.currentUserId());

        if (!passwordEncoder.matches(currentPassword, member.getPassword()))
        {
            throw new IllegalArgumentException("기존 비밀번호가 올바르지 않습니다.");
        }

        member.updatePassword(passwordEncoder.encode(newPassword));
    }

    @Transactional
    public Member join(MemberJoinServiceRequest request)
    {
        if (!request.password().equals(request.confirmPassword()))
        {
            throw new IllegalArgumentException("비밀번호 확인");
        }

        if (memberRepository.existsByEmail(request.email()))
        {
            throw new IllegalStateException("이미 있는 회원");
        }

        Member member = Member.builder()
                .memberRoles(List.of(request.role()))
                .email(request.email())
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

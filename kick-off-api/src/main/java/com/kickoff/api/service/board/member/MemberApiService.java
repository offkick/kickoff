package com.kickoff.api.service.board.member;

import com.kickoff.api.service.board.member.dto.UpdateMemberServiceRequest;
import com.kickoff.core.config.security.AuthUtil;
import com.kickoff.core.member.Member;
import com.kickoff.core.member.service.MemberJoinServiceRequest;
import com.kickoff.core.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberApiService {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    public Long join(MemberJoinServiceRequest request)
    {
        return memberService.join(request).getMemberId();
    }

    public Long updateMember(UpdateMemberServiceRequest request)
    {
        Member member = memberService.findById(AuthUtil.currentUserId());

        Member updateMember = Member.builder()
                .nickName(request.nickName())
                .build();

        member.update(updateMember);
        return member.getMemberId();
    }

    public void deleteMember(Long memberId)
    {
        Member member = memberService.findById(memberId);
        member.delete();
        memberService.delete(member);
    }

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

    @Transactional
    public void passwordChange(String currentPassword, String newPassword) {
        Member member = memberService.findById(AuthUtil.currentUserId());

        if (!passwordEncoder.matches(currentPassword, member.getPassword()))
        {
            throw new IllegalArgumentException("기존 비밀번호가 올바르지 않습니다.");
        }

        member.updatePassword(passwordEncoder.encode(newPassword));
    }
}

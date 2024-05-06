package com.kickoff.api.service.board.member;

import com.kickoff.api.service.board.member.dto.CreateMemberServiceRequest;
import com.kickoff.api.service.board.member.dto.UpdateMemberServiceRequest;
import com.kickoff.domain.member.Member;
import com.kickoff.domain.member.MemberRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
public class ApiMemberServiceTest {

    @Autowired
    ApiMemberService apiMemberService;

    @Autowired
    MemberRepository memberRepository;

    @DisplayName("멤버생성")
    @Test
    void createMember()
    {
        //given
        CreateMemberServiceRequest request = new CreateMemberServiceRequest(
                "nickName",
                "1234"
        );

        //when
        Long memberId = apiMemberService.createMember(request);

        //then
        assertThat(memberId).isNotNull();
    }

    @DisplayName("멤버삭제")
    @Test
    void deleteMember()
    {
        //given
        Member member = memberRepository.save(
                Member.builder()
                        .nickName("nickName")
                        .password("1234")
                        .build()
        );

        //when
        apiMemberService.deleteMember(member.getMemberId());

        //then
        List<Member> all = memberRepository.findAll();
        assertThat(all).isEmpty();
    }

    @DisplayName("멤버수정")
    @Test
    @Transactional
    void updateMember()
    {
        //given
        Member member = memberRepository.save(
                Member.builder()
                        .nickName("nickName")
                        .password("1234")
                        .build()
        );

        UpdateMemberServiceRequest request = new UpdateMemberServiceRequest(
                "updated",
                "2345",
                member.getMemberId()
        );

        //when
        Long memberId = apiMemberService.updateMember(request);

        //then
        assertThat(memberId).isNotNull();
    }

}

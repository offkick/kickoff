package com.kickoff.api.service.board.member;

import com.kickoff.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class MemberApiServiceTest {

    @Autowired
    MemberApiService memberApiService;

    @Autowired
    MemberRepository memberRepository;

    // TODO 변경
//    @DisplayName("멤버생성")
//    @Test
//    void createMember()
//    {
//        //given
//        CreateMemberServiceRequest request = new CreateMemberServiceRequest(
//                "nickName",
//                "1234"
//        );
//
//        //when
//        Long memberId = memberApiService.join(request);
//
//        //then
//        assertThat(memberId).isNotNull();
//    }
//
//    @DisplayName("멤버삭제")
//    @Test
//    void deleteMember()
//    {
//        //given
//        Member member = memberRepository.save(
//                Member.builder()
//                        .nickName("nickName")
//                        .password("1234")
//                        .build()
//        );
//
//        //when
//        memberApiService.deleteMember(member.getMemberId());
//
//        //then
//        List<Member> all = memberRepository.findAll();
//        assertThat(all).isEmpty();
//    }
//
//    @DisplayName("멤버수정")
//    @Test
//    @Transactional
//    void updateMember()
//    {
//        //given
//        Member member = memberRepository.save(
//                Member.builder()
//                        .nickName("nickName")
//                        .password("1234")
//                        .build()
//        );
//
//        UpdateMemberServiceRequest request = new UpdateMemberServiceRequest(
//                "updated",
//                "2345"
//        );
//
//        //when
//        Long memberId = memberApiService.updateMember(member.getMemberId(),request);
//
//        //then
//        assertThat(memberId).isNotNull();
//    }

}

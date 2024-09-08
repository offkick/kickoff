//package com.kickoff.api.service.board.member;
//
//import com.kickoff.core.member.MemberRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//@Transactional
//@SpringBootTest
//public class MemberApiServiceTest {
//
//    @Autowired
//    MemberCommandService memberCommandService;
//
//    @Autowired
//    MemberRepository memberRepository;
//
//    // TODO 변경
//    @DisplayName("멤버생성")
//    @Test
//    void createMember()
//    {
//        //given
//        MemberJoinServiceRequest request = new MemberJoinServiceRequest(
//                "nickName2",
//                "1234",
//                "1234",
//                "1234@naver.com",
//                MemberRole.ADMIN
//        );
//
//        //when
//        Long memberId = memberCommandService.join(request);
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
//                        .email("nickname@naver.com")
//                        .nickname("nickName3")
//                        .password("1234")
//                        .build()
//        );
//
//        //when
//        memberCommandService.deleteMember(member.getMemberId());
//
//        //then
//        Optional<Member> byId = memberRepository.findById(member.getMemberId());
//        assertThat(byId).isEmpty();
//    }
//
////
////    @DisplayName("멤버수정")
////    @Test
////    @Transactional
////    void updateMember()
////    {
////        //given
////        Member member = memberRepository.save(
////                Member.builder()
////                        .nickname("nickname")
////                        .password("1234")
////                        .build()
////        );
////
////        UpdateMemberServiceRequest request = new UpdateMemberServiceRequest(
////                "updated",
////                "2345"
////        );
////
////        //when
////        Long memberId = memberApiService.updateMember(member.getMemberId(),request);
////
////        //then
////        assertThat(memberId).isNotNull();
////    }
//
//}

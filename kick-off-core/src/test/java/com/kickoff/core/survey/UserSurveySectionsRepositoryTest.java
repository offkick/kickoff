package com.kickoff.core.survey;

import com.kickoff.core.TestConfiguration;
import com.kickoff.core.member.Member;
import com.kickoff.core.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ActiveProfiles("domain")
@ContextConfiguration(classes = TestConfiguration.class)
@SpringBootTest
public class UserSurveySectionsRepositoryTest {
    @Autowired
    UserSurveySectionsRepository userSurveySectionsRepository;
    @Autowired
    SurveySectionsRepository surveySectionsRepository;
    @Autowired
    SurveyHeadersRepository surveyHeadersRepository;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void save()
    {

        Member member = Member.builder()
                .nickName("name")
                .password("password")
                .build();
        memberRepository.save(member);

        SurveyHeaders headers = SurveyHeaders.builder()
                .surveyName("프리미어리그")
                .instruction("3/31 토트넘 vs 루턴타운")
                .build();
        surveyHeadersRepository.save(headers);

        SurveySections surveySections = SurveySections.builder()
                .sectionName("최고의 선수를 뽑아 주세요!")
                .sectionTitle("선수 평가")
                .sectionRequiredYn(true)
                .build();
        surveySections.setSurveyHeaders(headers);
        surveySectionsRepository.save(surveySections);

        UserSurveySections userSurveySections = UserSurveySections.builder()
                .surveySections(surveySections)
                .memberId(member.getMemberId())
                .build();

        userSurveySectionsRepository.save(userSurveySections);

        Assertions.assertThat(userSurveySections.getUserSurveySectionsId()).isNotNull();
    }

}

package com.kickoff.core.survey;

import com.kickoff.core.TestConfiguration;
import com.kickoff.core.member.Member;
import com.kickoff.core.member.MemberRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ActiveProfiles("core")
@ContextConfiguration(classes = TestConfiguration.class)
@SpringBootTest
public class SurveyRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    SurveyHeadersRepository surveyHeadersRepository;

    @Autowired
    SurveySectionsRepository surveySectionsRepository;

    @Autowired
    QuestionsRepository questionsRepository;

    @Autowired
    OptionGroupsRepository optionGroupsRepository;

    @Autowired
    SurveyInputTypeRepository surveyInputTypeRepository;

    @Autowired
    QuestionOptionsRepository questionOptionsRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    UserSurveySectionsRepository userSurveySectionsRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @Commit
    public void save()
    {
        // given
        SurveyInputType surveyInputType = new SurveyInputType();
        surveyInputType.setInputTypeName("select");
        surveyInputTypeRepository.save(surveyInputType);
        SurveyInputType surveyInputType2 = new SurveyInputType();
        surveyInputType2.setInputTypeName("text");
        surveyInputTypeRepository.save(surveyInputType2);

        Member member = Member.builder()
                .nickname("name")
                .password("password")
                .build();
        memberRepository.save(member);


        SurveyHeaders headers = SurveyHeaders.builder()
                .surveyName("월드컵 2차 예선전 한국 태국전 2차전 경기 평가")
                .instruction("월드컵 2차 예선전 한국 - 태국 2차전")
                .build();
        surveyHeadersRepository.save(headers);

        SurveySections surveySections = SurveySections.builder()
                .sectionName("최고의 선수를 뽑아 주세요!")
                .sectionTitle("선수 평가")
                .sectionRequiredYn(true)
                .build();
        surveySections.setSurveyHeaders(headers);
        surveySectionsRepository.save(surveySections);

        OptionGroups optionGroups = OptionGroups.builder()
                .optionGroupName("공격수")
                .build();

        OptionChoices op1 = OptionChoices.builder()
                .optionGroups(optionGroups)
                .optionChoiceName("손흥민")
                .build();

        OptionChoices op2 = OptionChoices.builder()
                .optionGroups(optionGroups)
                .optionChoiceName("주민규")
                .build();

        OptionChoices op3 = OptionChoices.builder()
                .optionGroups(optionGroups)
                .optionChoiceName("황희찬")
                .build();

        optionGroups.addOptionChoice(op1);
        optionGroups.addOptionChoice(op2);
        optionGroups.addOptionChoice(op3);
        optionGroupsRepository.save(optionGroups);

        Questions questions1 = Questions.builder()
                .answerRequiredYn(false)
                .multipleOptionAnswers(true)
                .surveySection(surveySections)
                .surveyInputTypeId(surveyInputType.getSurveyInputTypeId())
                .questionName("최고의 공격수는?")
                .optionGroupsId(optionGroups.getOptionGroupId())
                .build();

        questionsRepository.save(questions1);

        QuestionOptions questionOptions = QuestionOptions.builder()
                .optionChoices(op1)
                .questions(questions1)
                .build();

        questionOptionsRepository.save(questionOptions);

        Answer answer = Answer.builder()
                .questionOptions(questionOptions)
                .answerYn(true)
                .memberId(member.getMemberId())
                .build();

        answerRepository.save(answer);

        UserSurveySections userSurveySections = UserSurveySections.builder()
                .surveySections(surveySections)
                .memberId(member.getMemberId())
                .build();

        // when
        userSurveySectionsRepository.save(userSurveySections);
    }
}

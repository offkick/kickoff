package com.kickoff.domain.survey;

import com.kickoff.domain.TestConfiguration;
import com.kickoff.domain.member.Member;
import com.kickoff.domain.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ActiveProfiles("domain")
@ContextConfiguration(classes = TestConfiguration.class)
@SpringBootTest
public class AnswerRepositoryTest {
    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    QuestionOptionsRepository questionOptionsRepository;

    @Autowired
    QuestionsRepository questionsRepository;

    @Autowired
    SurveySectionsRepository surveySectionsRepository;

    @Autowired
    SurveyInputTypeRepository surveyInputTypeRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    SurveyHeadersRepository surveyHeadersRepository;

    @Autowired
    OptionGroupsRepository optionGroupsRepository;

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

        SurveyInputType surveyInputType = new SurveyInputType();
        surveyInputType.setInputTypeName("select");
        surveyInputTypeRepository.save(surveyInputType);

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
                .optionChoiceName("히샬리숑")
                .build();

        OptionChoices op3 = OptionChoices.builder()
                .optionGroups(optionGroups)
                .optionChoiceName("브레넌존슨")
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

        Assertions.assertThat(answer.getAnswerId()).isNotNull();
    }

}

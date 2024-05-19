package com.kickoff.api.service.survey;

import com.kickoff.api.controller.survey.dto.SurveyRequest;
import com.kickoff.core.member.Member;
import com.kickoff.core.member.MemberRepository;
import com.kickoff.core.survey.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class SurveyRequestServiceTest {
    @Autowired
    SurveyService sut;

    @Autowired
    QuestionOptionsRepository questionOptionsRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    SurveyHeadersRepository surveyHeadersRepository;

    @Autowired
    SurveyInputTypeRepository surveyInputTypeRepository;

    @Autowired
    SurveySectionsRepository surveySectionsRepository;

    @Autowired
    OptionGroupsRepository optionGroupsRepository;

    @Autowired
    QuestionsRepository questionsRepository;

    @Autowired
    SurveyService surveyRequestService;
    @Autowired
    AnswerRepository answerRepository;


    @Test
    public void surveyTest()
    {
        // given
        Member member = Member.builder()
                .nickName("name")
                .password("password")
                .build();
        memberRepository.save(member);

        SurveyInputType surveyInputType = new SurveyInputType();
        surveyInputType.setInputTypeName("select");
        surveyInputTypeRepository.save(surveyInputType);

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


        OptionGroups optionGroups = OptionGroups.builder()
                .optionGroupName("공격수")
                .build();

        OptionChoices op1 = OptionChoices.builder()
                .optionGroups(optionGroups)
                .optionChoiceName("손흥민")
                .build();
        optionGroups.addOptionChoice(op1);
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

        SurveyRequest.SurveyAnswerRequest request = new SurveyRequest.SurveyAnswerRequest(
                surveySections.getSurveySectionId(),
                questions1.getQuestionId(),
                op1.getOptionChoicesId(),
                1,
                null,
                true

        );
        List<SurveyRequest.SurveyAnswerRequest> answerList = Arrays.asList(request);

        SurveyRequest surveyRequest = new SurveyRequest(
                headers.getSurveyHeaderId(),
                answerList
        );

        // when
        surveyRequestService.survey(member.getMemberId(), surveyRequest);

        List<Answer> answers = answerRepository.findAll();

        Assertions.assertThat(answers).hasSize(1);
    }
}

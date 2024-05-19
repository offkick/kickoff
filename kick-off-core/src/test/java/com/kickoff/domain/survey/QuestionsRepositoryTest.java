package com.kickoff.domain.survey;

import com.kickoff.domain.TestConfiguration;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ActiveProfiles("domain")
@ContextConfiguration(classes = TestConfiguration.class)
@SpringBootTest
public class QuestionsRepositoryTest {
    @Autowired
    QuestionsRepository questionsRepository;

    @Autowired
    SurveyInputTypeRepository surveyInputTypeRepository;

    @Autowired
    SurveySectionsRepository surveySectionsRepository;

    @Autowired
    SurveyHeadersRepository surveyHeadersRepository;

    @Autowired
    OptionGroupsRepository optionGroupsRepository;

    @Commit
    @Test
    public void save()
    {
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

        SurveySections surveySections2 = SurveySections.builder()
                .sectionName("워스트를 뽑아 주세요!")
                .sectionTitle("선수 평가")
                .sectionRequiredYn(true)
                .build();

        surveySections.setSurveyHeaders(headers);
        surveySections2.setSurveyHeaders(headers);
        surveySectionsRepository.save(surveySections);
        surveySectionsRepository.save(surveySections2);

        OptionGroups optionGroups = OptionGroups.builder()
                .optionGroupName("공격수")
                .build();
        optionGroupsRepository.save(optionGroups);

        Questions questions1 = Questions.builder()
            .answerRequiredYn(false)
            .multipleOptionAnswers(true)
            .surveySection(surveySections)
            .surveyInputTypeId(surveyInputType.getSurveyInputTypeId())
            .questionName("최고의 공격수는?")
            .optionGroupsId(optionGroups.getOptionGroupId())
            .build();

        Questions questions2 = Questions.builder()
                .answerRequiredYn(false)
                .multipleOptionAnswers(true)
                .surveySection(surveySections)
                .surveyInputTypeId(surveyInputType.getSurveyInputTypeId())
                .questionName("최고의 수비수는?")
                .optionGroupsId(optionGroups.getOptionGroupId())
                .build();

        Questions questions3 = Questions.builder()
                .answerRequiredYn(false)
                .multipleOptionAnswers(true)
                .surveySection(surveySections2)
                .surveyInputTypeId(surveyInputType.getSurveyInputTypeId())
                .questionName("워스트 공격수는?")
                .optionGroupsId(optionGroups.getOptionGroupId())
                .build();

        Questions questions4 = Questions.builder()
                .answerRequiredYn(false)
                .multipleOptionAnswers(true)
                .surveySection(surveySections2)
                .surveyInputTypeId(surveyInputType.getSurveyInputTypeId())
                .questionName("워스트 수비수는?")
                .optionGroupsId(optionGroups.getOptionGroupId())
                .build();

        questionsRepository.save(questions1);
        questionsRepository.save(questions2);
        questionsRepository.save(questions3);
        questionsRepository.save(questions4);

        Assertions.assertThat(questions1.getQuestionId()).isNotNull();

    }
}

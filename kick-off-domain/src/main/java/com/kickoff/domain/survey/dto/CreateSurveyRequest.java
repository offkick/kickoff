package com.kickoff.domain.survey.dto;

import com.kickoff.domain.survey.Questions;
import com.kickoff.domain.survey.SurveyHeaders;
import com.kickoff.domain.survey.SurveySections;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CreateSurveyRequest {
    private String surveyName;
    private  String instructions;
    private List<SurveySectionRequest> surveySections;

    public SurveyHeaders getSurveyHeaders()
    {
        return SurveyHeaders.builder()
                .instruction(instructions)
                .surveyName(surveyName)
                .build();
    }

    @NoArgsConstructor
    @Data
    public static class SurveySectionRequest
    {
        private String sectionName;
        private String sectionTitle;
        private boolean sectionRequiredYn;

        private List<QuestionRequest> questionRequestList;

        public SurveySections of(SurveyHeaders surveyHeaders)
        {
            return SurveySections
                    .builder()
                    .surveyHeaders(surveyHeaders)
                    .sectionTitle(sectionTitle)
                    .sectionName(sectionName)
                    .sectionRequiredYn(sectionRequiredYn)
                    .build();
        }
    }

    @NoArgsConstructor
    @Data
    public static class QuestionRequest
    {
        private String questionName;
        private String questionSubText;
        private boolean answerRequiredYn;
        private Long inputTypesId;
        private List<Long> optionChoiceIds;
        private Long optionsGroupId;
        public Questions of(SurveySections surveySections)
        {
            return Questions.builder()
                    .questionSubtext(questionSubText)
                    .questionName(questionName)
                    .answerRequiredYn(answerRequiredYn)
                    .surveySection(surveySections)
                    .optionGroupsId(optionsGroupId)
                    .surveyInputTypeId(inputTypesId)
                    .build();
        }
    }
}

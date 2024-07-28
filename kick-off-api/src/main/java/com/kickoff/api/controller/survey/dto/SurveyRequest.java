package com.kickoff.api.controller.survey.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class SurveyRequest {
    private Long surveyHeadersId;
    private List<SurveyAnswerRequest> surveyAnswerRequestList;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class SurveyAnswerRequest
    {
        private Long surveySectionId;
        private Long questionId;
        private Long questionChoicesId;
        private Integer answerNumeric;
        private String answerText;
        private boolean answerYn;
    }
}

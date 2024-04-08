package com.kickoff.domain.survey.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SurveyHeaderDTO {
    private Long surveyHeadersId;
    private String surveyName;
    private String instruction;
    private List<SurveySectionDTO> surveySectionDTOS;

    public SurveyHeaderDTO(
            Long surveyHeadersId,
            String surveyName,
            String instruction,
            List<SurveySectionDTO> surveySectionDTO
    ) {
        this.surveyHeadersId = surveyHeadersId;
        this.surveyName = surveyName;
        this.instruction = instruction;
        this.surveySectionDTOS = surveySectionDTOS;
    }

    @Data
    @NoArgsConstructor
    public static class SurveySectionDTO
    {
        private Long surveySectionId;
        private String sectionName;
        private String sectionTitle;
        private Long questionId;
        private String questionName;

        public SurveySectionDTO(
                Long surveySectionId,
                String sectionName,
                String sectionTitle,
                Long questionId,
                String questionName
        ) {
            this.surveySectionId = surveySectionId;
            this.sectionName = sectionName;
            this.sectionTitle = sectionTitle;
            this.questionId = questionId;
            this.questionName = questionName;
        }
    }
}

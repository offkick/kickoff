package com.kickoff.api.controller.survey.dto;

import com.kickoff.domain.survey.dto.SurveyHeaderDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class SurveyResponse {
    private Long surveyHeadersId;
    private String surveyName;
    private String instruction;
    private List<SurveySectionResponse> surveySectionResponses;

    public static SurveyResponse of(SurveyHeaderDTO dto)
    {
        List<SurveyHeaderDTO.SurveySectionDTO> surveySectionDTOS = dto.getSurveySectionDTOS();

        Map<Long, List<SurveyHeaderDTO.SurveySectionDTO>> res = surveySectionDTOS.stream()
                .collect(Collectors.groupingBy(
                        SurveyHeaderDTO.SurveySectionDTO::getSurveySectionId
                ));

        List<SurveySectionResponse> surveySectionResponses =
                res.entrySet().stream()
                .map(s -> SurveySectionResponse.of(s.getKey(), s.getValue()))
                .toList();

        return new SurveyResponse(
                dto.getSurveyHeadersId(),
                dto.getSurveyName(),
                dto.getInstruction(),
                surveySectionResponses
        );
    }

    @Data
    @NoArgsConstructor
    public static class SurveySectionResponse
    {
        private Long surveySectionId;
        private String sectionName;
        private String sectionTitle;
        private List<QuestionsResponse> questionsResponses;

        public SurveySectionResponse(
                Long surveySectionId,
                String sectionTitle,
                String sectionName,
                List<QuestionsResponse> questionsResponses
        ) {
            this.surveySectionId = surveySectionId;
            this.sectionTitle = sectionTitle;
            this.sectionName = sectionName;
            this.questionsResponses = questionsResponses;
        }

        public static SurveySectionResponse of(Long surveySectionId, List<SurveyHeaderDTO.SurveySectionDTO> value)
        {
            List<QuestionsResponse> questionsResponses = value.stream()
                    .map(t-> new QuestionsResponse(
                            t.getQuestionId(),
                            t.getQuestionName()
                    ))
                    .collect(Collectors.toList());

            return new SurveySectionResponse(
                    surveySectionId,
                    value.get(0).getSectionTitle(),
                    value.get(0).getSectionName(),
                    questionsResponses
            );
        }
    }

    @Data
    @NoArgsConstructor
    public static class QuestionsResponse
    {
        private Long questionId;
        private String questionName;
        public QuestionsResponse(Long questionId, String questionName)
        {
            this.questionId = questionId;
            this.questionName = questionName;
        }
    }
}

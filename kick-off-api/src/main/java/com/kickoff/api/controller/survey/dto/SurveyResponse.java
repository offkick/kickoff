package com.kickoff.api.controller.survey.dto;

import com.kickoff.domain.survey.dto.SurveyHeaderDTO;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class SurveyResponse {
    private Long surveyHeadersId;
    private String surveyName;
    private String instruction;
    private List<SurveySectionResponse> surveySectionResponses;

    public static SurveyResponse of(SurveyHeaderDTO dto) {
        dto.getSurveySectionDTOS().stream()
                .map(s->SurveySectionResponse)
                .collect(Collectors.toList());
        return new SurveyResponse(
                dto.getSurveyHeadersId(),
                dto.getSurveyName(),
                dto.getInstruction()

        );
    }

    public static class SurveySectionResponse
    {
        private Long surveySectionId;
        private String sectionName;
        private String sectionTitle;
    }

    public
}

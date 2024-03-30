package com.kickoff.domain.survey;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter
public class SurveyHeaders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long surveyHeaderId;

    private String surveyName;

    private String instruction;

    @Builder
    public SurveyHeaders(Long surveyHeaderId, String surveyName, String instruction) {
        this.surveyHeaderId = surveyHeaderId;
        this.surveyName = surveyName;
        this.instruction = instruction;

    }
}

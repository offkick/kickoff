package com.kickoff.core.survey;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Entity
@Getter
@ToString
public class SurveyHeaders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long surveyHeaderId;

    private String surveyName;

    private String instruction;

    @Column(columnDefinition = "boolean default false")
    private Boolean isDeleted;

    @Builder
    public SurveyHeaders(Long surveyHeaderId, String surveyName, String instruction) {
        this.surveyHeaderId = surveyHeaderId;
        this.surveyName = surveyName;
        this.instruction = instruction;
        isDeleted = Boolean.FALSE;

    }

    public void delete()
    {
        if (!isDeleted)
        {
            this.isDeleted = true;
        }
    }
}

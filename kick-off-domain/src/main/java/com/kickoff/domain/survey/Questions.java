package com.kickoff.domain.survey;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;
    private String questionName;
    private String questionSubtext;
    private boolean answerRequiredYn;
    private boolean multipleOptionAnswers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "surveySection_id")
    private SurveySections surveySection;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "optionGroups_id")
    private OptionGroups optionGroups;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "surveyInputType_id")
    private SurveyInputType surveyInputType;

    @Builder
    public Questions(Long questionId, String questionName, String questionSubtext, boolean answerRequiredYn, boolean multipleOptionAnswers, SurveySections surveySection, OptionGroups optionGroups, SurveyInputType surveyInputType) {
        this.questionId = questionId;
        this.questionName = questionName;
        this.questionSubtext = questionSubtext;
        this.answerRequiredYn = answerRequiredYn;
        this.multipleOptionAnswers = multipleOptionAnswers;
        this.surveySection = surveySection;
        this.optionGroups = optionGroups;
        this.surveyInputType = surveyInputType;
    }
}

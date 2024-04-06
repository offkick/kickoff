package com.kickoff.domain.survey;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 예시
 * questionName : 최고 공격수를 뽑아주세요
 * surveyInputType : option, text ..
 *
 */
@Entity
@NoArgsConstructor
@Getter
public class Questions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;
    private String questionName;
    private String questionSubtext;
    private Boolean answerRequiredYn;
    private Boolean multipleOptionAnswers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_section_id")
    private SurveySections surveySection;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_groups_id")
    private OptionGroups optionGroups;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_inputType_id")
    private SurveyInputType surveyInputType;

    @Builder
    public Questions(
            Long questionId,
            String questionName,
            String questionSubtext,
            Boolean answerRequiredYn,
            Boolean multipleOptionAnswers,
            SurveySections surveySection,
            OptionGroups optionGroups,
            SurveyInputType surveyInputType
    ) {
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

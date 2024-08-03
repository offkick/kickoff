package com.kickoff.core.survey;

import com.kickoff.core.BaseEntity;
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
public class Questions extends BaseEntity {

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

    @Column(columnDefinition = "boolean default false")
    private Boolean isDeleted;

    private Long optionGroupsId;
    private Long surveyInputTypeId;

    @Builder
    public Questions(
            Long questionId,
            String questionName,
            String questionSubtext,
            Boolean answerRequiredYn,
            Boolean multipleOptionAnswers,
            SurveySections surveySection,
            Long optionGroupsId,
            Long surveyInputTypeId
    ) {
        this.questionId = questionId;
        this.questionName = questionName;
        this.questionSubtext = questionSubtext;
        this.answerRequiredYn = answerRequiredYn;
        this.multipleOptionAnswers = multipleOptionAnswers;
        this.surveySection = surveySection;
        this.optionGroupsId = optionGroupsId;
        this.surveyInputTypeId = surveyInputTypeId;
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

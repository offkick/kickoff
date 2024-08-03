package com.kickoff.core.survey;

import com.kickoff.core.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/*
 * 예시
 * sectionName: 최고의 선수를 뽑아주세요
 * sectionTitle: 선수 평가
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class SurveySections extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long surveySectionId;

    private String sectionName;
    private String sectionTitle;
    private boolean sectionRequiredYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_headers_id")
    private SurveyHeaders surveyHeaders;

    @Column(columnDefinition = "boolean default false")
    private Boolean isDeleted;

    @Builder
    public SurveySections(Long surveySectionId, String sectionName, String sectionTitle, boolean sectionRequiredYn, SurveyHeaders surveyHeaders) {
        this.surveySectionId = surveySectionId;
        this.sectionName = sectionName;
        this.sectionTitle = sectionTitle;
        this.sectionRequiredYn = sectionRequiredYn;
        this.surveyHeaders = surveyHeaders;
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

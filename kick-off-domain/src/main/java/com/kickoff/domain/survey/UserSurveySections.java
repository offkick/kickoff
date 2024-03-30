package com.kickoff.domain.survey;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(indexes = @Index(name="user_survey_sections_idx",unique = true,columnList = "member_id, survey_sections_id"))
public class UserSurveySections {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userSurveySectionsId;

    @ManyToOne
    @JoinColumn(name = "survey_sections_id")
    private SurveySections surveySections;

    private Long memberId;

    @Builder
    public UserSurveySections(Long userSurveySectionsId, SurveySections surveySections, Long memberId) {
        this.userSurveySectionsId = userSurveySectionsId;
        this.surveySections = surveySections;
        this.memberId = memberId;
    }
}

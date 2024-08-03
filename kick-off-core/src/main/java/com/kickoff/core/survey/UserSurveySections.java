package com.kickoff.core.survey;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(indexes = @Index(name="user_survey_sections_idx", unique = true, columnList = "member_id, survey_sections_id"))
public class UserSurveySections {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userSurveySectionsId;

    @ManyToOne
    @JoinColumn(name = "survey_sections_id")
    private SurveySections surveySections;

    @Column(name = "member_id")
    private Long memberId;

    @Column(columnDefinition = "boolean default false")
    private Boolean isDeleted;


    @Builder
    public UserSurveySections(Long userSurveySectionsId, SurveySections surveySections, Long memberId) {
        this.userSurveySectionsId = userSurveySectionsId;
        this.surveySections = surveySections;
        this.memberId = memberId;
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

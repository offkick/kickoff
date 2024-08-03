package com.kickoff.core.survey;

import com.kickoff.core.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SurveyInputType extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long surveyInputTypeId;
    private String inputTypeName;

    @Column(columnDefinition = "boolean default false")
    private Boolean isDeleted;

    public void delete()
    {
        if (!isDeleted)
        {
            this.isDeleted = true;
        }
    }
}
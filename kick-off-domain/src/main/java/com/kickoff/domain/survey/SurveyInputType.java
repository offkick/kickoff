package com.kickoff.domain.survey;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SurveyInputType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long surveyInputTypeId;
    private String inputTypeName;
}


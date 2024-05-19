package com.kickoff.core.survey;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SurveySectionsRepository extends JpaRepository<SurveySections, Long> {
    List<SurveySections> findBySurveyHeaders(SurveyHeaders surveyHeaders);
}

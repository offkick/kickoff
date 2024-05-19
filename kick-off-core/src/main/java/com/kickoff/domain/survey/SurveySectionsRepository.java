package com.kickoff.domain.survey;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SurveySectionsRepository extends JpaRepository<SurveySections, Long> {
    List<SurveySections> findBySurveyHeaders(SurveyHeaders surveyHeaders);
}

package com.kickoff.core.survey;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionsRepository extends JpaRepository<Questions, Long> {
    List<Questions> findBySurveySection(SurveySections surveySections);
}

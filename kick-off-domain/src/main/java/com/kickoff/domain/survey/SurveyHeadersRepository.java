package com.kickoff.domain.survey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SurveyHeadersRepository extends JpaRepository<SurveyHeaders, Long> {
}

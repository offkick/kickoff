package com.kickoff.domain.survey.service;

import com.kickoff.domain.survey.SurveyHeaders;
import com.kickoff.domain.survey.SurveyHeadersRepository;
import com.kickoff.domain.survey.SurveySections;
import com.kickoff.domain.survey.SurveySectionsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SurveyHeaderService {
    private final SurveyHeadersRepository surveyHeadersRepository;
    private final SurveySectionsRepository surveySectionsRepository;

    @Transactional(readOnly = true)
    public SurveyHeaders findById(Long surveyHeaderId)
    {
        return surveyHeadersRepository.findById(surveyHeaderId).orElseThrow(EntityNotFoundException::new);
    }

    public List<SurveySections> findBySurveyHeaders(SurveyHeaders surveyHeaders)
    {
        return surveySectionsRepository.findBySurveyHeaders(surveyHeaders);
    }
}

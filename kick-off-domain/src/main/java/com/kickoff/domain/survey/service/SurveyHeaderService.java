package com.kickoff.domain.survey.service;

import com.kickoff.domain.survey.*;
import com.kickoff.domain.survey.dto.CreateSurveyRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SurveyHeaderService {
    private final SurveyHeadersRepository surveyHeadersRepository;
    private final SurveySectionsRepository surveySectionsRepository;
    private final QuestionsRepository questionsRepository;

    @Transactional(readOnly = true)
    public SurveyHeaders findById(Long surveyHeaderId)
    {
        return surveyHeadersRepository.findById(surveyHeaderId).orElseThrow(EntityNotFoundException::new);
    }

    public void createSurveyHeaders(CreateSurveyRequest request)
    {
        // 1 depth - SurveyHeaders
        SurveyHeaders surveyHeaders = request.getSurveyHeaders();
        surveyHeadersRepository.save(surveyHeaders);

        // 2 depth - SurveySections
        for (CreateSurveyRequest.SurveySectionRequest surveySectionRequest : request.getSurveySections())
        {
              SurveySections sections = surveySectionRequest.of(surveyHeaders);
              surveySectionsRepository.save(sections);

            // 3 depth - Questions
            for (CreateSurveyRequest.QuestionRequest questionRequest : surveySectionRequest.getQuestionRequestList())
            {
                Questions questions = questionRequest.of(sections);
                questionsRepository.save(questions);
            }
        }
    }
}

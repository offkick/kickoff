package com.kickoff.core.survey.service;

import com.kickoff.core.survey.Questions;
import com.kickoff.core.survey.QuestionsRepository;
import com.kickoff.core.survey.SurveyHeaders;
import com.kickoff.core.survey.SurveySections;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AnswerQuestionSurveyValidatorImpl implements AnswerQuestionSurveyValidator {
    private final QuestionsRepository questionsRepository;

    @Override
    public void validate(Long surveyHeadersId, Long surveySectionId, Long questionId, Long optionChoicesId)
    {
        Questions questions = questionsRepository.findById(questionId).orElseThrow();
        SurveySections surveySection = questions.getSurveySection();
        SurveyHeaders surveyHeaders = surveySection.getSurveyHeaders();

        if(!surveyHeaders.getSurveyHeaderId().equals(surveyHeadersId)
                || !surveySection.getSurveySectionId().equals(surveySectionId)
        ) {
            throw new RuntimeException("survey 없음");
        }

        if(!optionChoicesId.equals(questions.getOptionGroupsId()))
        {
            throw new RuntimeException("invalid optionChoicesId");
        }

    }
}

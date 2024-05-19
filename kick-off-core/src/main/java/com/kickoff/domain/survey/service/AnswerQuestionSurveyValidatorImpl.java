package com.kickoff.domain.survey.service;

import com.kickoff.domain.survey.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

package com.kickoff.domain.survey.service;

import com.kickoff.domain.survey.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AnswerQuestionSurveyValidatorImpl implements AnswerQuestionSurveyValidator {
    private final OptionChoicesRepository optionChoicesRepository;
    private final QuestionsRepository questionsRepository;

    @Override
    public void validate(Long surveyHeadersId, Long surveySectionId, Long questionId, Long optionChoicesId)
    {
        Questions questions = questionsRepository.findById(questionId).orElseThrow();
        SurveySections surveySection = questions.getSurveySection();
        SurveyHeaders surveyHeaders = surveySection.getSurveyHeaders();
        OptionChoices optionChoices = optionChoicesRepository.findById(optionChoicesId).orElseThrow();

        if(!surveyHeaders.getSurveyHeaderId().equals(surveyHeadersId)
                || !surveySection.getSurveySectionId().equals(surveySectionId)
        ) {
            throw new RuntimeException("survey 없음");
        }
        questions.getOptionGroups().getOptionChoicesList().stream()
                .map()
    }
}

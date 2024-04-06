package com.kickoff.domain.survey.service;

import com.kickoff.domain.survey.OptionChoices;
import com.kickoff.domain.survey.Questions;
import com.kickoff.domain.survey.SurveyHeaders;
import com.kickoff.domain.survey.SurveySections;

public interface AnswerQuestionSurveyValidator {
    void validate(Long surveyHeadersId, Long surveySectionId, Long questionId, Long optionChoicesId);
}

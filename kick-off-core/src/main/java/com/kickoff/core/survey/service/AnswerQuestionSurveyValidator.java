package com.kickoff.core.survey.service;

public interface AnswerQuestionSurveyValidator {
    void validate(Long surveyHeadersId, Long surveySectionId, Long questionId, Long optionChoicesId);
}

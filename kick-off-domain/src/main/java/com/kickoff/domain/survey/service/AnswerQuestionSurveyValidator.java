package com.kickoff.domain.survey.service;

public interface AnswerQuestionSurveyValidator {
    void validate(Long surveyHeadersId, Long surveySectionId, Long questionId, Long optionChoicesId);
}

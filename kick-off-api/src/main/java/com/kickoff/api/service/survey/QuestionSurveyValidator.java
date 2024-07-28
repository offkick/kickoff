package com.kickoff.api.service.survey;

public interface QuestionSurveyValidator {
    void validate(
                Long memberId,
                Long questionId,
                Long optionChoicesId,
                int answerNumeric,
                String answerText,
                boolean answerYn
    );
}

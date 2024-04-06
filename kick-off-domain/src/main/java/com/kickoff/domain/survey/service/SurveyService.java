package com.kickoff.domain.survey.service;

import com.kickoff.domain.survey.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class SurveyService {
    private final AnswerQuestionSurveyValidator answerQuestionSurveyValidator;
    private final AnswerService answerService;
    private final QuestionOptionsRepository questionOptionsRepository;

    public void answerQuestionSurvey(
            Long memberId,
            Long surveyHeadersId,
            Long surveySectionId,
            Long questionId,
            Long optionChoicesId,
            int answerNumeric,
            String answerText,
            boolean answerYn
    ) {
        answerQuestionSurveyValidator.validate(
                surveyHeadersId,
                surveySectionId,
                questionId,
                optionChoicesId
        );

        QuestionOptions questionOptions = questionOptionsRepository.findByQuestionsIdAndAndOptionChoicesId(
                optionChoicesId, questionId)
                .orElseThrow();

        answerService.create(
                memberId,
                answerNumeric,
                answerText,
                answerYn,
                questionOptions
        );
    }
}

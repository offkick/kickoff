package com.kickoff.core.survey.service;

import com.kickoff.core.survey.Answer;
import com.kickoff.core.survey.QuestionOptions;
import com.kickoff.core.survey.QuestionOptionsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class SurveyAnswerService {
    private final AnswerQuestionSurveyValidator answerQuestionSurveyValidator;
    private final QuestionOptionsRepository questionOptionsRepository;
    private final AnswerService answerService;

    public Answer answerQuestionSurvey(
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

        return answerService.create(
                memberId,
                answerNumeric,
                answerText,
                answerYn,
                questionOptions
        );
    }
}

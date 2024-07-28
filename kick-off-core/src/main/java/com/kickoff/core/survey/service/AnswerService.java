package com.kickoff.core.survey.service;

import com.kickoff.core.survey.Answer;
import com.kickoff.core.survey.AnswerRepository;
import com.kickoff.core.survey.QuestionOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public Answer create(Long memberId, int answerNumeric, String answerText, boolean answerYn, QuestionOptions questionOptions)
    {
        Answer answer = Answer.builder()
                .memberId(memberId)
                .answerNumber((long) answerNumeric)
                .answerYn(answerYn)
                .answerText(answerText)
                .questionOptions(questionOptions)
                .build();

        return answerRepository.save(answer);
    }
}

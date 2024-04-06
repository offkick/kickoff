package com.kickoff.domain.survey.service;

import com.kickoff.domain.survey.Answer;
import com.kickoff.domain.survey.AnswerRepository;
import com.kickoff.domain.survey.QuestionOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public void create(Long memberId, int answerNumeric, String answerText, boolean answerYn, QuestionOptions questionOptions)
    {
        Answer answer = Answer.builder()
                .memberId(memberId)
                .answerNumber(Long.valueOf(answerNumeric))
                .answerYn(answerYn)
                .questionOptions(questionOptions)
                .build();

        answerRepository.save(answer);
    }
}

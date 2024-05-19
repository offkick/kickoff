package com.kickoff.api.service.survey;

import com.kickoff.core.survey.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class QuestionsFindService {
    private QuestionsRepository questionsRepository;
    private QuestionOptionsRepository questionOptionsRepository;

    public List<Questions> findQuestions(SurveySections surveySections)
    {
        return questionsRepository.findBySurveySection(surveySections);
    }

    public List<QuestionOptions> findQuestionOptions(Questions questions)
    {
        return questionOptionsRepository.findByQuestions(questions);
    }
}

package com.kickoff.api.service.survey;


import com.kickoff.core.survey.QuestionOptionsRepository;
import com.kickoff.core.survey.service.AnswerQuestionSurveyValidator;
import com.kickoff.core.survey.service.AnswerService;
import com.kickoff.core.survey.service.SurveyAnswerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SurveyServiceTest {

    @Autowired
    SurveyAnswerService sut;

    @Autowired
    AnswerQuestionSurveyValidator answerQuestionSurveyValidator;

    @Autowired
    QuestionOptionsRepository questionOptionsRepository;

    @Autowired
    AnswerService answerService;

    // TODO : integration test 작성
    @Test
    public void answerSurvey()
    {
//        Member member = Member.builder().build()
//        sut.answerQuestionSurvey(1L, 1L, 1L, 1L, 1L, 10, "", Boolean.TRUE);
    }

}

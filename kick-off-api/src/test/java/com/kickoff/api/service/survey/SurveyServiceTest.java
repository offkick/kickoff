package com.kickoff.api.service.survey;


import com.kickoff.domain.member.Member;
import com.kickoff.domain.survey.QuestionOptionsRepository;
import com.kickoff.domain.survey.service.AnswerQuestionSurveyValidator;
import com.kickoff.domain.survey.service.AnswerService;
import com.kickoff.domain.survey.service.SurveyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SurveyServiceTest {

    @Autowired
    SurveyService sut;

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

package com.kickoff.domain.survey;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Component
public class SurveyQuerydslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public void searchSurvey()
    {
        QSurveyHeaders surveyHeaders = QSurveyHeaders.surveyHeaders;
        QSurveySections surveySections = QSurveySections.surveySections;
        QQuestions questions = QQuestions.questions;
        List<Questions> questionsList = jpaQueryFactory.select(questions)
                .from(questions)
                .innerJoin(questions.surveySection, surveySections).fetchJoin()
                .innerJoin(surveySections.surveyHeaders, surveyHeaders).fetchJoin()
                .where(questions.surveySection.surveyHeaders.surveyHeaderId.eq(1L))
                .fetch();

        questionsList
                .stream()
                .collect(Collectors.groupingBy(s -> s.getSurveySection().getSurveySectionId()));
    }
}
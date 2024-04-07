package com.kickoff.domain.survey;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import static com.querydsl.core.group.GroupBy.*;

@Component
@RequiredArgsConstructor
public class SurveyHeadersQueryDslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public void findSurveyHeadersById(Long surveyHeadersId)
    {
        QSurveyHeaders surveyHeaders = QSurveyHeaders.surveyHeaders;
        QSurveySections surveySections = QSurveySections.surveySections;
        QQuestions questions = QQuestions.questions;
        QSurveyInputType qSurveyInputType = QSurveyInputType.surveyInputType;
        QQuestionOptions questionOptions = QQuestionOptions.questionOptions;

        List<SurveyHeaderDTO> result =  jpaQueryFactory.select(surveySections)
                .from(surveySections)
                .innerJoin(surveySections.surveyHeaders, surveyHeaders)
                .join(questions).on(questions.surveySection.surveySectionId.eq(surveySections.surveySectionId))
//                    .innerJoin(questions.surveyInputType, qSurveyInputType)
//                    .innerJoin(questionOptions).on(questionOptions.questions.questionId.eq(questions.questionId))
                .where(surveyHeaders.surveyHeaderId.eq(surveyHeadersId))
                .distinct()
                .transform(
                        GroupBy.groupBy(surveyHeaders.surveyHeaderId).list(
                                Projections.constructor(
                                        SurveyHeaderDTO.class,
                                        surveyHeaders.surveyHeaderId,
                                        surveyHeaders.surveyName,
                                        surveyHeaders.instruction,
                                        list(
                                                Projections.constructor(
                                                        SurveyHeaderDTO.SurveySectionDTO.class,
                                                        surveySections.surveySectionId,
                                                        surveySections.sectionName,
                                                        surveySections.sectionTitle,
                                                        list(
                                                                Projections.constructor(
                                                                        SurveyHeaderDTO.QuestionDTO.class,
                                                                        questions.questionId,
                                                                        questions.questionName
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                );
        System.out.println("result = " + result);

    }
}

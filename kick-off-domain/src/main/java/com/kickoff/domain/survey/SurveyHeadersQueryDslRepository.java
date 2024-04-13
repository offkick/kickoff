package com.kickoff.domain.survey;

import com.kickoff.domain.survey.dto.SurveyHeaderDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import static com.querydsl.core.group.GroupBy.*;

@Component
@RequiredArgsConstructor
public class SurveyHeadersQueryDslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public void test()
    {
//        QQuestions questions = QQuestions.questions;
//        List<Questions> fetch = jpaQueryFactory.select(QQuestions.questions)
//                .innerJoin(questions.surveySection).fetchJoin()
//                .innerJoin(questions.optionGroups).fetchJoin()
//                .innerJoin(questions.surveyInputType).fetchJoin()
//                .leftJoin(questions.surveySection.surveyHeaders).fetchJoin()
//                .where(questions.surveySection.surveyHeaders.surveyHeaderId.eq(1L))
//                .fetch();
    }

    @Nullable
    public SurveyHeaderDTO findSurveyHeadersById(Long surveyHeadersId)
    {
        QSurveyHeaders surveyHeaders = QSurveyHeaders.surveyHeaders;
        QSurveySections surveySections = QSurveySections.surveySections;
        QQuestions questions = QQuestions.questions;
        QSurveyInputType qSurveyInputType = QSurveyInputType.surveyInputType;
        QQuestionOptions questionOptions = QQuestionOptions.questionOptions;

        List<SurveyHeaderDTO> transform = jpaQueryFactory.select(surveySections)
                .from(surveySections)
                .innerJoin(surveySections.surveyHeaders, surveyHeaders)
                .join(questions).on(questions.surveySection.surveySectionId.eq(surveySections.surveySectionId))
                .where(surveyHeaders.surveyHeaderId.eq(surveyHeadersId))
                .transform(
                        groupBy(surveyHeaders.surveyHeaderId).list(
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
                                                        questions.questionId,
                                                        questions.questionName
                                                )
                                        )
                                )
                        )
                );

        if (transform.size() > 1)
        {
            throw new RuntimeException();
        }

        return transform.isEmpty() ? null :  transform.get(0);
    }
}

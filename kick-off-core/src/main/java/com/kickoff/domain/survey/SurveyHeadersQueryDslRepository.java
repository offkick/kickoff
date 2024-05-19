package com.kickoff.domain.survey;

import com.kickoff.domain.survey.dto.SurveyHeaderDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.querydsl.core.group.GroupBy.*;

@Component
@RequiredArgsConstructor
public class SurveyHeadersQueryDslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public SurveyHeaderDTO searchSurvey()
    {
        QSurveyHeaders qSurveyHeaders = QSurveyHeaders.surveyHeaders;
        QSurveySections qSurveySections = QSurveySections.surveySections;
        QQuestions qQuestions = QQuestions.questions;

        List<Questions> questionsList = jpaQueryFactory.select(qQuestions)
                .from(qQuestions)
                .innerJoin(qQuestions.surveySection, qSurveySections).fetchJoin()
                .innerJoin(qSurveySections.surveyHeaders, qSurveyHeaders).fetchJoin()
                .where(qQuestions.surveySection.surveyHeaders.surveyHeaderId.eq(1L))
                .fetch();

        Map<SurveySections, List<Questions>> questionMap = questionsList.stream()
                .collect(Collectors.groupingBy(Questions::getSurveySection));

        SurveyHeaderDTO dto = new SurveyHeaderDTO();
        SurveyHeaders surveyHeaders = questionsList.stream()
                .findFirst()
                .orElseThrow()
                .getSurveySection().getSurveyHeaders();

        dto.setSurveyName(surveyHeaders.getSurveyName());
        dto.setInstruction(surveyHeaders.getInstruction());
        dto.setSurveyHeadersId(surveyHeaders.getSurveyHeaderId());

        for (Map.Entry<SurveySections, List<Questions>> entry : questionMap.entrySet())
        {
            SurveySections surveySections = entry.getKey();
            List<SurveyHeaderDTO.SurveySectionDTO> surveySectionDTOS = new ArrayList<>();

            for (Questions questions : entry.getValue())
            {
                surveySectionDTOS.add(
                        SurveyHeaderDTO.SurveySectionDTO.builder()
                                .surveySectionId(surveySections.getSurveySectionId())
                                .questionId(questions.getQuestionId())
                                .questionName(questions.getQuestionName())
                                .sectionName(surveySections.getSectionName())
                                .sectionTitle(surveySections.getSectionTitle())
                                .build()
                );
            }
            dto.setSurveySectionDTOS(surveySectionDTOS);
        }

        return dto;
    }
}

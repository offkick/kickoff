package com.kickoff.api.service.survey;

import com.kickoff.api.controller.survey.dto.SurveyRequest;
import com.kickoff.domain.member.Member;
import com.kickoff.domain.survey.*;
import com.kickoff.domain.survey.service.AnswerService;
import com.kickoff.domain.survey.service.MemberService;
import com.kickoff.domain.survey.service.SurveyHeaderService;
import com.kickoff.domain.survey.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class SurveyRequestService {
    private final SurveyService surveyService;
    private final MemberService memberService;
    private final SurveyHeaderService surveyHeaderService;

    public void survey(Long memberId, SurveyRequest request)
    {
        Member member = memberService.findById(memberId);

        SurveyHeaders surveyHeaders = surveyHeaderService.findById(request.getSurveyHeadersId());

        for (SurveyRequest.SurveyAnswerRequest surveyAnswerRequest : request.getSurveyAnswerRequestList())
        {
            surveyService.answerQuestionSurvey(
                    member.getMemberId(),
                    surveyHeaders.getSurveyHeaderId(),
                    surveyAnswerRequest.getSurveySectionId(),
                    surveyAnswerRequest.getQuestionId(),
                    surveyAnswerRequest.getQuestionChoicesId(),
                    surveyAnswerRequest.getAnswerNumeric(),
                    surveyAnswerRequest.getAnswerText(),
                    surveyAnswerRequest.isAnswerYn()
            );
        }
    }
}

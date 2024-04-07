package com.kickoff.api.service.survey;

import com.kickoff.api.controller.survey.dto.SurveyRequest;
import com.kickoff.domain.member.Member;
import com.kickoff.domain.survey.*;
import com.kickoff.domain.survey.service.MemberFindService;
import com.kickoff.domain.survey.service.SurveyHeaderService;
import com.kickoff.domain.survey.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class SurveyRequestService {
    private final SurveyService surveyService;
    private final MemberFindService memberService;
    private final SurveyHeaderService surveyHeaderService;

    public void survey(Long memberId, SurveyRequest request)
    {
        Member member = memberService.findById(memberId);

        SurveyHeaders surveyHeaders = surveyHeaderService.findById(request.getSurveyHeadersId());

        for (SurveyRequest.SurveyAnswerRequest surveyAnswerRequest : request.getSurveyAnswerRequestList())
        {
           Answer answer = surveyService.answerQuestionSurvey(
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
